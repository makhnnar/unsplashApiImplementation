package com.pedrogomez.develepersfinder.view.hitsslist.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedrogomez.develepersfinder.R
import com.pedrogomez.develepersfinder.databinding.ViewHitListBinding
import com.pedrogomez.develepersfinder.models.db.UserPicture
import com.pedrogomez.develepersfinder.utils.PageScrollListener
import com.pedrogomez.develepersfinder.utils.extensions.print
import com.pedrogomez.develepersfinder.view.hitsslist.view.listadapter.HitViewHolder
import com.pedrogomez.develepersfinder.view.hitsslist.view.listadapter.HitsAdapter
import com.pedrogomez.develepersfinder.view.hitsslist.view.swipecontroler.SwipeController

class HitsListView : ConstraintLayout,
    HitViewHolder.OnClickItemListener,
    PageScrollListener.OnScrollEvents,
    SwipeController.SwipeControllerActions{

    private lateinit var pageScrollListener: PageScrollListener

    lateinit var binding : ViewHitListBinding

    private lateinit var hitsAdapter : HitsAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager

    var onHitsListActions : OnHitsListActions? = null

    val swipeController : SwipeController = SwipeController()

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        binding = ViewHitListBinding.inflate(
                LayoutInflater.from(context),
                this
        )
        val a = context.obtainStyledAttributes(
                attrs,
                R.styleable.HitsListView,
                defStyle,
                0
        )
        initRecyclerView()
        a.recycle()
    }

    private fun initRecyclerView() {
        hitsAdapter = HitsAdapter(
                this,
                this
        )
        linearLayoutManager = LinearLayoutManager(context)
        pageScrollListener = PageScrollListener(
                linearLayoutManager,
                this,
                true
        )
        binding.rvPokeItems.apply{
            adapter = hitsAdapter
            layoutManager = linearLayoutManager
        }
        binding.srlContainer.setOnRefreshListener {
            binding.srlContainer.isRefreshing = false
            pageScrollListener.initFields()
            onHitsListActions?.loadAgain()
        }
        binding.btnToTop.setOnClickListener {
            binding.rvPokeItems.smoothScrollToPosition(0)
        }
        binding.rvPokeItems.addOnScrollListener(
                pageScrollListener
        )
        val itemTouchhelper = ItemTouchHelper(
                swipeController
        )
        itemTouchhelper.attachToRecyclerView(
                binding.rvPokeItems
        )
        binding.rvPokeItems.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun onDraw(
                    c: Canvas,
                    parent: RecyclerView,
                    state: RecyclerView.State
            ) {
                swipeController!!.onDraw(c!!)
            }
        })
    }

    fun hideBtnToTop(){
        binding.btnToTop.hide()
    }

    fun showBtnToTop(){
        binding.btnToTop.show()
    }

    fun showLoader(){
        binding.srlContainer.isRefreshing = false
        binding.srlContainer.isEnabled = false
        binding.pbLoading.show()
        pageScrollListener.enablePaging(false)
    }

    fun hideLoader(){
        binding.srlContainer.isRefreshing = false
        binding.srlContainer.isEnabled = true
        binding.pbLoading.remove()
        pageScrollListener.enablePaging(true)
    }

    fun setData(hitItems: List<UserPicture>){
        "size in adapter ${hitItems.size}".print()
        hitsAdapter.submitList(hitItems)
    }

    interface OnHitsListActions{
        fun loadMore(page: Int)
        fun loadAgain()
        fun goToItemDetail(data: UserPicture)
        fun deleted(hitItem:UserPicture)
    }

    override fun goToItemDetail(data: UserPicture) {
        onHitsListActions?.goToItemDetail(data)
    }

    override fun onLoadMore(currentPage: Int) {
        onHitsListActions?.loadMore(currentPage)
    }

    override fun scrollIsOnTop(isOnTop: Boolean) {
        if(isOnTop){
            hideBtnToTop()
            return
        }
        showBtnToTop()
    }

    override fun deleted(hitItem:UserPicture) {
        onHitsListActions?.deleted(hitItem)
    }

}