package com.example.storygram.view.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.storygram.R
import java.util.regex.Pattern

class EditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {
    init{
        when(id){
            R.id.password_edit_text -> {
                addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (s.toString().length < 8){
                            error = context.getString(R.string.password_alert)
                        }else{
                            error = null
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {

                    }

                })
            }
            R.id.email_edit_text -> {
                addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (!isValidEmail(s.toString())) {
                            error = context.getString(R.string.invalid_email)
                        } else {
                            error = null
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {

                    }

                })
            }
        }
    }
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        val pattern = Pattern.compile(emailRegex)
        return pattern.matcher(email).matches()
    }
}