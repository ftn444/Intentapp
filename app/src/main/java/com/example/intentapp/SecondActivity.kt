package com.example.intentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intentapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    companion object{
        const val EXTRA_ADDRESS = "extra_address"
    }
    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        result -> if (result.resultCode == Activity.RESULT_OK){
            val data = result.data
            val name = data?.getStringExtra(MainActivity.EXTRA_NAME)
            val address = data?.getStringExtra(EXTRA_ADDRESS)
        binding.txtName.text="Selamat datang $name";
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name=intent.getStringExtra(MainActivity.EXTRA_NAME)
        with(binding){
            txtName.text = name
            btnToThirdActivity.setOnClickListener {
                val intent = Intent(this@SecondActivity,
                    ThirdActivity::class.java).apply {
                        putExtra(MainActivity.EXTRA_NAME, name)
                }
                launcher.launch(intent)
            }
        }
    }
}