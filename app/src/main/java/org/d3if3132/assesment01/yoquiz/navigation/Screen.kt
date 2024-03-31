package org.d3if3132.assesment01.yoquiz.navigation

sealed class Screen(val route:String){
    data object SignIn : Screen(Graph.SIGN_IN)
    data object Home : Screen(Graph.HOME)
    data object Quiz : Screen(Graph.QUIZ)
    data object Result : Screen(Graph.RESULT)

    data object About : Screen(Graph.ABOUT)
}