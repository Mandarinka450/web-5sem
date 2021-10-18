package com.example.a3_laba


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.a3_laba.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity(), ActivityCallback {
    lateinit var binding: ActivityMainBinding
    var fragmentsList: List<Fragment> =
        listOf(Fragment1.newInstance(), Fragment2.newInstance(), Fragment3.newInstance(),
            Fragment4.newInstance())
    var buttonsList: MutableList<Button> = mutableListOf()

    private fun buttonsManager(n: Int) {
        for (i in 0 until buttonsList.count())
            buttonsList[i].isEnabled = i <= n
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonsList.add(0, binding.fragment1)
        buttonsList.add(1, binding.fragment2)
        buttonsList.add(2, binding.fragment3)
        buttonsList.add(3, binding.fragment4)
        setClickListenerForButtons()

        val fragment_1 = Fragment1()
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment_1)
        transaction.commit()
        buttonsManager(0)

    }

    private fun setClickListenerForButtons() {
        buttonsList[0].setOnClickListener {
            supportFragmentManager
                .beginTransaction().replace(R.id.container, fragmentsList[0]).commit()
            buttonsManager(0)

        }
        buttonsList[1].setOnClickListener {
            supportFragmentManager
                .beginTransaction().replace(R.id.container, fragmentsList[1]).commit()
            buttonsManager(1)

        }
        buttonsList[2].setOnClickListener {
            supportFragmentManager
                .beginTransaction().replace(R.id.container, fragmentsList[2]).commit()
            buttonsManager(2)

        }
        buttonsList[3].setOnClickListener {
            supportFragmentManager
                .beginTransaction().replace(R.id.container, fragmentsList[3]).commit()
            buttonsManager(3)

        }

    }




    override fun showNextFragment2() {
        val fragment_2 = Fragment2()
        val transaction  = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment_2)
        transaction.commit()
        buttonsManager(1)



    }
    override fun showNextFragment3() {
        val fragment_3 = Fragment3()
        val transaction  = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment_3)
        transaction.commit()
        buttonsManager(2)


    }
    override fun showNextFragment1() {
        val fragment_1 = Fragment1()
        val transaction  = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment_1)
        transaction.commit()
        buttonsManager(0)


    }
    override fun showNextFragment4() {
        val fragment_4 = Fragment4()
        val transaction  = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment_4)
        transaction.commit()
        buttonsManager(3)


    }

    override fun ExitFragment() {
        finishAffinity()
        System.exit(0);
    }



}

interface ActivityCallback{
    fun ExitFragment()
    fun showNextFragment1()
    fun showNextFragment2()
    fun showNextFragment3()
    fun showNextFragment4()
}