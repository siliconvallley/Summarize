package com.dh.summarize.fragment.android

import android.content.Intent
import android.os.Handler
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dh.summarize.R
import com.dh.summarize.activity.android.MovieDetailActivity
import com.dh.summarize.adapter.EndlessRecyclerOnScrollListener
import com.dh.summarize.adapter.LoadMoreAdapter
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.entity.Movie
import kotlinx.android.synthetic.main.material_design_fragment.*
import java.util.ArrayList


/**
 * @author 86351
 * @date 2019/10/28
 * @description  综合使用MaterialDesign的控件
 */
class MaterialDesignFragment : BaseFragment() {
    private var mMovieList: MutableList<Movie.SubjectsBean>? = null
    private var start = 0
    private var end = 20
    private var loadMoreAdapter: LoadMoreAdapter? = null

    companion object {
        fun getInstance() = MaterialDesignFragment()
    }

    override fun getLayoutId(): Int {
        return R.layout.material_design_fragment
    }

    override fun initViews(view: View) {

    }

    override fun initListener() {

    }

    override fun initData() {
        mMovieList = arrayListOf()

        swipeRefresh.isRefreshing = true
        setAdapter()
        swipeRefresh.setOnRefreshListener {
            mMovieList?.clear()
            end = 20
            getData(start, end, true)
        }

        getData(start, end, true)
        rvList.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMoreData() {
                loadMoreAdapter?.setLoadState(loadMoreAdapter?.LOADING ?: 1)
                Handler().postDelayed({
                    if (mMovieList?.size!! < 250) {
                        getData(end, 20, false)
                    } else {
                        loadMoreAdapter?.setLoadState(loadMoreAdapter?.LOADING_END ?: 3)
                    }
                }, 2000)
            }

        })
    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        rvList.layoutManager = layoutManager
    }

    private fun getData(start: Int, end: Int, isRefresh: Boolean) {
        mMovieList?.add(Movie.SubjectsBean())
        mMovieList?.add(Movie.SubjectsBean())
        mMovieList?.add(Movie.SubjectsBean())
        mMovieList?.add(Movie.SubjectsBean())
        mMovieList?.add(Movie.SubjectsBean())
        mMovieList?.add(Movie.SubjectsBean())
        mMovieList?.add(Movie.SubjectsBean())
        mMovieList?.add(Movie.SubjectsBean())
        mMovieList?.add(Movie.SubjectsBean())
        mMovieList?.add(Movie.SubjectsBean())

        if (loadMoreAdapter == null) {
            loadMoreAdapter =
                LoadMoreAdapter(mMovieList as ArrayList<Movie.SubjectsBean>?, mActivity)
            rvList.adapter = loadMoreAdapter
        } else {
            loadMoreAdapter?.notifyDataSetChanged()
        }

        swipeRefresh.isRefreshing = false
        initItemListener(mMovieList)

        loadMoreAdapter?.setLoadState(loadMoreAdapter?.LOADING_COMPLETE ?: 2)
    }

    private fun initItemListener(mMovieList: MutableList<Movie.SubjectsBean>?) {
        loadMoreAdapter?.setOnItemClickListener { view, position ->
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                mActivity,
                view?.findViewById(R.id.iv_icon)!!,
                "basic"  // 共享动画  在activity_movie_detail中给ImageView指定相同的属性，共享动画
            )

            val intent = Intent(mActivity, MovieDetailActivity::class.java)
            intent.putExtra("URL", mMovieList?.get(position)?.images?.medium)
            intent.putExtra("NAME", mMovieList?.get(position)?.title)
            startActivity(intent, optionsCompat.toBundle())
        }
    }

}