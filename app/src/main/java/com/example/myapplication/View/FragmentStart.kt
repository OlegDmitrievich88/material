package com.example.myapplication.View

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.myapplication.Model.PictureOfTheDayData
import com.example.myapplication.R
import com.example.myapplication.ViewModel.ViewModelPictureFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentStart: Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var viewModel: ViewModelPictureFragment

    companion object {
        fun newInstance() = FragmentStart()
        private var isMain = true
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModelPictureFragment::class.java)
        viewModel.getData()
            .observe(viewLifecycleOwner) { renderData(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
       // setBottomAppBar(view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout){
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
    private fun setBottomAppBar(view: View){
        val bar: BottomAppBar = view.findViewById(R.id.bottom_app_bar)
        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        val context = activity as MainActivity
        context.setSupportActionBar(bar)

        setHasOptionsMenu(true)

        fab.setOnClickListener {

            if (isMain){
                isMain = false
                bar.navigationIcon = null
                bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_directions_run_24))

            }else{

                isMain = true
                bar.navigationIcon =
                    ContextCompat.getDrawable(context,R.drawable.ic_baseline_accessibility_new_24)
                bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_accessibility_new_24))
            }





        }


    }

    private fun renderData(data: PictureOfTheDayData){
        val img: EquilateralImageView = requireView().findViewById(R.id.image_view)
        when(data){
            is PictureOfTheDayData.Success ->{
                val  serverResponseData = data.serverResponseData
                val url =serverResponseData.url

                if (url.isEmpty()){
                    toast("link is empty")
                }else{

                    img.load(url){
                        lifecycle(this@FragmentStart)
                        error(R.drawable.ic_launcher_background)
                        placeholder(R.drawable.ic_launcher_foreground)
                    }
                }
            }
            is PictureOfTheDayData.Error->{
                toast(data.error.message)
            }
            is PictureOfTheDayData.Loading->{}
        }
    }

    private fun Fragment.toast(string: String?){
        Toast.makeText(context,string, Toast.LENGTH_SHORT).apply{
            setGravity(Gravity.BOTTOM,0,258)
            show()
        }
    }
















}