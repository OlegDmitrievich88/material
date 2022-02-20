package com.example.myapplication.View

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.setting_fragment.*

class SettingFragment: Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
        private const val THEME_NAME = "theme_name"
        private const val THEME = "theme"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //   val settingTheme: SharedPreferences? =
     //       context?.getSharedPreferences(THEME_NAME, Context.MODE_PRIVATE)
//        val themedContext = ContextThemeWrapper(
//            context,
//            R.style.Theme_MyAppCustom
//        )
//        val themedContextTwo = ContextThemeWrapper(
//            context,
//            R.style.Theme_MyApplication
//        )

//        chipGroup.setOnCheckedChangeListener { chipGroup, position ->
//            chipGroup.findViewById<Chip>(position)?.let{
//                when{
//                    orange.isChecked -> {activity?.setTheme(R.style.Theme_MyAppCustom)
//                        activity?.recreate()
//                    }
//                }
//            }
//        }

        button_accept.setOnClickListener {
            when{
                orange.isChecked -> {
                    change("one")
                    activity?.recreate()
                }
                some.isChecked -> {
                    change("two")
                    activity?.recreate()

                }
                some_theme.isChecked -> {
                    change("three")
                    activity?.recreate()

                }
            }
        }
    }

    private fun change(s: String) {
        val settingTheme: SharedPreferences? =
            context?.getSharedPreferences(THEME, Context.MODE_PRIVATE)
        val edit: SharedPreferences.Editor? = settingTheme?.edit()
        edit?.putString(THEME_NAME, s)
        edit?.apply()
    }
}