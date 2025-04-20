package com.satta.d_matka.error


/**
 * Created by Mohammad sajjad on 12-05-2021.
 * mohammadsajjad679@gmail.com
 */



// defined default error messages for something is wrong when error is not specified
data class StandardError (val title: String = "Unknown error", val displayError: String = "Something is wrong please try again later")