package com.marcus.materialescolar.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.Toast
import com.marcus.materialescolar.App
import com.marcus.materialescolar.AppExecutors
import com.marcus.materialescolar.R
import com.marcus.materialescolar.model.Material
import kotlinx.android.synthetic.main.add_fragment.*

/**
 * Created by Marcus on 05-Feb-18.
 *
 */
class AddFragment : Fragment() {

    private val TAG = "AddFragment"
    private lateinit var mActivity: AppCompatActivity
    private val mHandler = object: Handler(Looper.getMainLooper()) {

        override fun handleMessage(msg: Message?) {
            when (msg!!.what) {
                AppExecutors.TASK_COMPLETE -> { finish() }
                AppExecutors.TASK_ERROR -> { handleError() }
                else -> {
                    // ignore
                }
            }
            super.handleMessage(msg)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mActivity = activity as AppCompatActivity
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater!!.inflate(R.layout.add_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_add_fragment_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_done -> save()
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        val name = material_name.text.toString()
        if (name.trim().isEmpty()) {
            Toast.makeText(context, "Insert the material name!", Toast.LENGTH_SHORT).show()
            return
        }

        val material = Material(name = material_name.text.toString(),
                unityPrice = 2.5, marca = "Faber Castell")

        val app = this.activity.application as App
        val appExecutors = app.appExecutors
        appExecutors.diskIO.submit({
                app.getDatabase().runInTransaction({
                    val resultSet = app.getDatabase().materialDao().insert(listOf(material))
                    lateinit var message: Message
                    if (resultSet.isNotEmpty()) {
                        message = mHandler.obtainMessage(AppExecutors.TASK_COMPLETE)
                    } else {
                        message = mHandler.obtainMessage(AppExecutors.TASK_ERROR)
                    }
                    message.sendToTarget()
                })
            }
        )
    }

    fun finish() {
        Log.d(TAG, "finish is called from AddFragment")
    }

    private fun handleError() {
        Toast.makeText(context, "There is an error when saving material!",
                Toast.LENGTH_SHORT).show()
    }
}