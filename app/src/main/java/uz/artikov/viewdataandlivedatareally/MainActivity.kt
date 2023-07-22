package uz.artikov.viewdataandlivedatareally

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uz.artikov.viewdataandlivedatareally.databinding.ActivityMainBinding
import uz.artikov.viewdataandlivedatareally.utils.UserResource

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    lateinit var myViewModel: MyViewModel

    lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]


        myViewModel.getUserList().observe(this, Observer {

            binding.textId.text = it.toString()

            when (it) {
                is UserResource.Error -> {

                    binding.textId.text = it.message

                }

                is UserResource.Loading -> {

                    binding.textId.text = "Loading..."

                }

                is UserResource.Success -> {

                    binding.textId.text = it.toString()


                }
            }

        })

    }

}