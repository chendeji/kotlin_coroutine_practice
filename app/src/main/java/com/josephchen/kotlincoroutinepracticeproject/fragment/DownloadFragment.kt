package com.josephchen.kotlincoroutinepracticeproject.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.josephchen.kotlincoroutinepracticeproject.R
import com.josephchen.kotlincoroutinepracticeproject.databinding.FragmentDownloadBinding
import com.josephchen.kotlincoroutinepracticeproject.databinding.FragmentHomeBinding
import com.josephchen.kotlincoroutinepracticeproject.download.DownloadManager
import com.josephchen.kotlincoroutinepracticeproject.download.DownloadStatus
import kotlinx.coroutines.flow.collect
import java.io.File

class DownloadFragment : Fragment() {

    private val binding : FragmentDownloadBinding by lazy {
        FragmentDownloadBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = "https://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fc-ssl.duitang.com%252Fuploads%252Fblog%252F202111%252F17%252F20211117092914_579a7.thumb.1000_0.jpeg%26refer%3Dhttp%253A%252F%252Fc-ssl.duitang.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1673151867%26t%3D1b877e425fca55b7cea0ae6ba110d628&thumburl=https%3A%2F%2Fimg2.baidu.com%2Fit%2Fu%3D3241450202%2C4190299177%26fm%3D253%26fmt%3Dauto%26app%3D120%26f%3DJPEG%3Fw%3D500%26h%3D1082"
        lifecycleScope.launchWhenCreated {
            context?.apply {
                val file = File(getExternalFilesDir(null)?.path, "pic.JPG")
                DownloadManager.download(url, file).collect{ status ->
                    when(status) {
                        is DownloadStatus.Progress -> {
                            binding.apply {
                                progressbar.progress = status.value
                                "${status.value}%".also { tvProgress.text = it }
                            }
                        }
                        is DownloadStatus.Error -> {
                            Toast.makeText(this,status.throwable.message, Toast.LENGTH_LONG).show()
                        }
                        is DownloadStatus.Done -> {
                            binding.apply {
                                progressbar.progress = 100
                                "100%".also { tvProgress.text = it }
                            }
                            Toast.makeText(this,"下载完成", Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            Log.d("download fragment", "下载错误，未知原因")
                        }
                    }
                }
            }
        }
    }

}