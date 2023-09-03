package gr.thanflix.presentation.components


import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import dagger.hilt.android.internal.managers.FragmentComponentManager
import dagger.hilt.android.internal.managers.ViewComponentManager
import gr.thanflix.presentation.R


interface ThanflixAppBarListener {
    fun onBackButtonPressed(view: View): Boolean {
        return false
    }
    fun onMenuItem1Pressed(view: View) {}
}

class ThanflixAppBar(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    /** Views */
    internal var toolBar: ConstraintLayout
    internal var titleTextView: TextView
    internal var backButton: ImageView
    internal var menuItem1ImgView: ImageView


    /** private attributes **/
    private var listener: ThanflixAppBarListener? = null
    var enabledBackBtn: Boolean = true

    /** Style Properties **/
    var primaryTitle: String = "Default Val"
        set(value) {
            if (value.isEmpty())
                titleTextView.visibility = GONE
            else {
                titleTextView.visibility = VISIBLE
                titleTextView.text = value
            }
            field = value
        }

    var noNavAction: Boolean = false
        set(value) {
            if (value)
                backButton.visibility = View.GONE
            else
                backButton.visibility = View.VISIBLE
            field = value
        }

    @ColorRes
    var foregroundColor: Int = R.color.background_default_primary
        set(value) {
            field = value
            updateColors()
        }

    init {
        // inflates custom view
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.thanflix_app_bar_layout, this, true)
        toolBar = view.findViewById(R.id.customToolBar)
        titleTextView = view.findViewById(R.id.titleTxtView)
        backButton = view.findViewById(R.id.backBtnImg)
        menuItem1ImgView = view.findViewById(R.id.menuItem1)

        // obtain theme attributes
        context.theme.obtainStyledAttributes(attrs, R.styleable.thanflix_styleable_appbar, 0, 0)
            .apply {
                try {
                    // state attributes
                    primaryTitle = getString(R.styleable.thanflix_styleable_appbar_Title) ?: ""
                    // styling attributes
                    noNavAction = getBoolean(R.styleable.thanflix_styleable_appbar_NoNavAction, false)
                    foregroundColor = getResourceId(R.styleable.thanflix_styleable_appbar_ForegroundColor, R.color.tint_primary)
                } finally {
                    recycle()
                }
            }
        // set up view
        setUpMenuOptions()
    }

    private fun updateColors() {
        val c = resources.getColor(foregroundColor, null)
        val cstate = ColorStateList.valueOf(c)
        titleTextView.setTextColor(c)
        backButton.imageTintList = cstate
    }

    private fun setUpMenuOptions() {
        backButton.setOnClickListener {
            val c = if (context is ViewComponentManager.FragmentContextWrapper)
                    FragmentComponentManager.findActivity(context)
                else context

            if (listener?.onBackButtonPressed(it) == false)
                (c as? AppCompatActivity)?.onBackPressedDispatcher?.onBackPressed()

        }

        menuItem1ImgView.setOnClickListener {
            listener?.onMenuItem1Pressed(it)
        }
    }

    fun setAppBarListener(listener: ThanflixAppBarListener) {
        this.listener = listener
    }

    var menuItem1IsVisible: Boolean
        set(value) {
            menuItem1ImgView.visibility = if (value) View.VISIBLE else View.GONE
        }
        get() {
            return menuItem1ImgView.isVisible
        }

    companion object {
        const val NO_RESOURCE_CONST = -1
    }
}