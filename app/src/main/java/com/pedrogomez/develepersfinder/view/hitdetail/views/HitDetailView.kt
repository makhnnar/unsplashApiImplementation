package com.pedrogomez.develepersfinder.view.hitdetail.views

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.pedrogomez.develepersfinder.R
import com.pedrogomez.develepersfinder.databinding.ViewHitDetailBinding
import com.pedrogomez.develepersfinder.models.db.UserPicture
import com.pedrogomez.develepersfinder.utils.extensions.print

class HitDetailView : ConstraintLayout {

    lateinit var binding : ViewHitDetailBinding

    var onDetailActions : OnDetailActions? = null

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
        binding = ViewHitDetailBinding.inflate(
            LayoutInflater.from(context),
            this
        )
        val a = context.obtainStyledAttributes(
                attrs,
                R.styleable.HitDetailView,
                defStyle,
                0
        )

        a.recycle()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setData(userPicture: UserPicture){

    }

    interface OnDetailActions{
        fun onBackPressed()
    }
}