package com.example.instagrammo.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.lorem.LoremBean
import com.example.instagrammo.beans.business.notification.NotificationArguments
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.environment.networking.ApiClient
import com.example.instagrammo.prefs
import com.example.instagrammo.service.NotificationService
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.utils.listener.HeaderBackListener
import com.example.instagrammo.utils.listener.OnPostItemClickListener
import com.example.instagrammo.view.notifications.NotificationPost
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import com.example.instagrammo.view.views.add.AddFragment
import com.example.instagrammo.view.views.add.AddNewPostFragment
import com.example.instagrammo.view.views.add.ConfirmNewPostFragment
import com.example.instagrammo.view.views.add.OnImageItemClickListener
import com.example.instagrammo.view.views.follow.FollowFragment
import com.example.instagrammo.view.views.home.HomeFragment
import com.example.instagrammo.view.views.profile.ButtonEditProfileListener
import com.example.instagrammo.view.views.profile.EditProfileFragment
import com.example.instagrammo.view.views.profile.ProfileFragment
import com.example.instagrammo.view.views.search.SearchFragment
import com.example.instagrammo.views.BaseActivity
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_basehome.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BaseHomeActivity : BaseActivity(),
    OnPostItemClickListener, ButtonEditProfileListener, HeaderBackListener,
    OnImageItemClickListener {

    private val viewModel = MainViewModel()

    var previewGapNumberNotification : Int = 0

    private val mInterval = 5000

    private val mHandler: Handler = Handler()

    private lateinit var itemsPost : List<PostBean>

    private lateinit var image : ResponseBody

    private var mStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try  {viewModel.setStateEvent(MainStateEvent.GetNumberPost)}
             finally { mHandler.postDelayed(this, mInterval.toLong()) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basehome)
        addFragment(HomeFragment.newInstance, R.id.fragment_container)
        bottomMenuNavigationManager()
        startRepeatingTask()
        setObserverNumberPost()
        startForeground()
        //sendOnChannel1()
    }


    private fun bottomMenuNavigationManager(){

        bottom_nav.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.nav_home ->  {
                    replaceFragment(HomeFragment.newInstance, R.id.fragment_container)
                    true
                }
                R.id.nav_search -> {
                    replaceFragment(SearchFragment.newInstance, R.id.fragment_container)
                    true
                }
                R.id.nav_add -> {
                    replaceFragment(AddFragment.newInstance, R.id.fragment_container)
                    true
                }
                R.id.nav_follow -> {
                    replaceFragment(FollowFragment.newInstance, R.id.fragment_container)
                    stopNotificationService()
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment.newInstance, R.id.fragment_container)
                    true
                }
                else -> false
            }
        }
    }


    private fun setObserverNumberPost() {
        viewModel.dataStateNumberPosts.observe(this, Observer {  dataState ->
            when(dataState) {
                is DataState.Error ->  {}
                is DataState.Loading -> { }
                is DataState.Success -> {
                    if (prefs.firstLog) {
                        prefs.numberPosts = dataState.data.payload
                        prefs.firstLog = false
                        viewModel.setStateEvent(MainStateEvent.GetPostsEvent)
                    }
                    if(dataState.data.payload > prefs.numberPosts) {
                        previewGapNumberNotification = dataState.data.payload - (dataState.data.payload - prefs.numberPosts)
                        prefs.numberPosts = dataState.data.payload
                        viewModel.setStateEvent(MainStateEvent.GetPostsEvent)
                        setObserverPost()
                    }
                }
            }
        })
    }

    private fun setObserverPost(){
        viewModel.dataStatePost.observe(this, Observer { dataState ->
            when(dataState) {
                is DataState.Error ->  { }
                is DataState.Loading -> { }
                is DataState.Success -> {
                    itemsPost = dataState.data
                    startNotificationPost()
                }
            }
        })

    }

    private fun getNumberPost() {
        viewModel.setStateEvent(MainStateEvent.GetNumberPost)
    }

    private fun startRepeatingTask() {
        mStatusChecker.run()
    }

    private fun stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker)
    }

    override fun onPictureProfileItemListener(string: String) {
        Log.i("Ciao",string)
    }

    //bottone per aprire edit profile
    override fun OnButtonPressedListener(pressed: Boolean) {
        //replaceFragment(EditProfileFragment.newInstance, R.id.fragment_container)
        addFragment(EditProfileFragment.newInstance, R.id.fragment_container)
        //removeFragment(ProfileFragment.newInstance)
    }

    //rimuove fragment modifica profilo e apre profilo
    override fun removeFragmentListener(fragment: Fragment) {
        //replaceFragment(ProfileFragment.newInstance, R.id.fragment_container)
        removeFragment(fragment)
    }

    override fun onImageItemAddListener(image: LoremBean, tag: String) {
        val fragment = AddNewPostFragment.newInstance(image)
        replaceFragment(fragment, R.id.fragment_container)
        addToBackStackFragment(tag)
        //addFragment(fragment, R.id.fragment_container)
    }

    override fun onImageItemAddPostListener(image: LoremBean) {
        val fragment = ConfirmNewPostFragment.newInstance(image)
        addFragment(fragment, R.id.fragment_container)
    }

    override fun onImagePostsListener(fragment: Fragment) {
        removeFragment(fragment)
        popBackStack()
        replaceFragment(AddFragment.newInstance, R.id.fragment_container)
    }

    private fun stopNotificationService() {
        stopService(Intent(this, NotificationService.newInstance::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRepeatingTask()
    }

    override fun addFragmentListener() {
        addFragment(AddFragment.newInstance, R.id.fragment_container)
    }


    private fun startNotificationPost() {
        val notificationIntent = Intent(this, BaseHomeActivity::class.java)
        val notificationManager = NotificationManagerCompat.from(this)

        buildDataNotification().forEach {
            val notificationPost = NotificationPost(notificationIntent, this)
                .buildNotificationPost(it.nameProfile!!, it.description!!, it.iconProfile!!)
            notificationManager.notify(1, notificationPost)
/*
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(
                    this,
                    0,
                    notificationIntent,
                    0
                )

            val notificationPost = NotificationCompat.Builder(this, Constant.CONST_CHANNEL_ID_1)
                .setContentTitle(it.nameProfile!!)
                .setContentText(it.description!!)
                .setSmallIcon(R.mipmap.logo0)
                .setLargeIcon(it.iconProfile!!)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_SOCIAL)
                .setGroup(Constant.CONST_GROUP_APP_NOTIFICATION)
                .setContentIntent(pendingIntent)
                .build()
            notificationManager.notify(1, notificationPost)

 */
        }
    }

    private fun startForeground() {
        startService(Intent(this, NotificationService.newInstance::class.java))
    }

    private fun buildDataNotification() : ArrayList<NotificationArguments> {
        val sizePosts = itemsPost.size -1
        val notificationList : ArrayList<NotificationArguments> = ArrayList()

        for ( i in sizePosts downTo previewGapNumberNotification step 1) {

            var picture : Bitmap? = ContextCompat.getDrawable(this,R.drawable.user)!!.toBitmap()
/*
            viewModel.setStateEvent(MainStateEvent.GetImageEvent(url))
            setObserverImage()

            ApiClient.GetClient.getImage(url).enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    picture = ContextCompat.getDrawable(this@BaseHomeActivity,R.drawable.user)!!.toBitmap()
                    Log.i("debug", "ERRORE")
                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    picture = BitmapFactory.decodeStream(response.body()?.byteStream())
                    Log.i("debug", "ci sono")
                }
            })
*/

            Picasso.get()
                .load(itemsPost[i].picture)
                .resize(200,200)
                .transform(CircleTransform())
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    }

                    override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        if (bitmap != null) {
                            picture = bitmap
                        }
                    }

                })
            notificationList.add(
                NotificationArguments(itemsPost[i].profile.name!!, itemsPost[i].title, picture))
        }
        return notificationList
    }


    companion object {
        var newInstance : BaseActivity = BaseHomeActivity()
    }

}