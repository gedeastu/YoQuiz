package org.d3if3132.assesment01.yoquiz.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.d3if3132.assesment01.yoquiz.R
import org.d3if3132.assesment01.yoquiz.model.Answer
import org.d3if3132.assesment01.yoquiz.model.AnswerItem
import org.d3if3132.assesment01.yoquiz.model.Gender
import org.d3if3132.assesment01.yoquiz.model.Question

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel(){

    //DARK THEME VIEW MODEL
    //MutableStateOf
    //    var isDarkTheme by mutableStateOf(savedStateHandle.get<Boolean>("isDarkTheme") ?: false)
    //        private set
    //    fun changeTheme(){
    //        isDarkTheme = !isDarkTheme
    //    }

    //MutableStateFlow
    var isDarkTheme = savedStateHandle.getStateFlow("isDarkTheme",false)
    fun changeTheme(){
        savedStateHandle["isDarkTheme"] = !isDarkTheme.value
    }
    //    private var _isDarkTheme = MutableStateFlow(false)
    //    var isDarkTheme = _isDarkTheme.asStateFlow()
    //    fun changeTheme(){
    //        _isDarkTheme.value = !_isDarkTheme.value
    //    }


    //SIGN IN VIEW MODEL
    //Membuat state name
    var name by mutableStateOf("")
        private set
    //Membuat on Text Changed Name function
    fun onTextChangedName(nameValueName:String){
        name = nameValueName
    }
    //Membuat on empty name text field
    fun onEmptyName(){
        name = ""
    }
    //Membuat state namaError
    var namaError by mutableStateOf(false)


    //Membuat state tentangAnda
    var tentangAnda by mutableStateOf("")
        private set
    //Membuat on Text Changed Name function
    fun onTextChangedTentangAnda(nameValueTentangAnda:String){
        tentangAnda = nameValueTentangAnda
    }
    //Membuat on empty tentang anda text field
    fun onEmptyTentangAnda(){
        tentangAnda = ""
    }
    var tentangAndaError by mutableStateOf(false)

    //Membuat gender list
    val options = listOf(
        Gender(id = 1, gender = R.string.pria),
        Gender(id = 2, gender = R.string.wanita)
    )

    //Membuat icon gender list
    val iconGender = listOf(
        R.drawable.baseline_face_24,
        R.drawable.baseline_face_4_24
    )

    //Membuat expanded state
    var isExpanded by mutableStateOf(false)

    //Membuat selectedChoice state
    var selectedChoice by mutableStateOf(options[0].gender.toString())

    //Membuat selectedIcon state
    var selectedIcon by mutableIntStateOf(iconGender[0])

    //Membuat question list
    private val questions = listOf(
        Question(1, "What components are used to display multiple lists to select only one option?", R.drawable.screenshot_2024_03_31_at_10_20_48 ,listOf(
            AnswerItem('A',"ExposedDropdownMenuBox()"), AnswerItem('B'," Scaffold()"), AnswerItem('C',"LazyColumn()"), AnswerItem('D',"Checkbox()"), AnswerItem('E',"AlertDialog()")),"ExposedDropdownMenuBox()"),
        Question(2, "Which version of Material Design?",
            R.drawable.screenshot_2024_03_31_at_15_49_03,listOf(AnswerItem('A',"Material Design 1"), AnswerItem('B',"Material Design 2"), AnswerItem('C',"Material Design 3"), AnswerItem('D', "Material Design 4"), AnswerItem('E', "Material Design 5")),"Material Design 3"),
        Question(3, "What is Jetpack Compose on Kotlin?", R.drawable.screenshot_2024_03_31_at_15_56_15,listOf(
            AnswerItem('A',"Modern toolkit for building native Android UI"), AnswerItem('B',"It's jetpack from composing"), AnswerItem('C',"Framework to build a website"), AnswerItem('D',"A Programming Language"), AnswerItem('E',"A Framework on Android")),"Modern toolkit for building native Android UI")
    )


    // QUIZ VIEW MODEL

    // Membuat sebuah state bernilai default 0 bertipe Integer
    private val _currentQuestionIndex = mutableIntStateOf(0)
    private val currentQuestionIndex : State<Int> get() = _currentQuestionIndex


    // Membuat sebuah state bernilai default List kosong bertipe Answer
    private var _answers = mutableStateOf<List<Answer>>(emptyList())
    private val answers: State<List<Answer>> get() = _answers

    //Membuat fungsi currentQuestion untuk mentrigger tombol next question
    val currentQuestion: Question
        get() = questions[currentQuestionIndex.value]


    // Membuat fungsi pertanyaan selanjutnya
    fun nextQuestion(){
        if (_currentQuestionIndex.intValue < questions.size - 1){
            _currentQuestionIndex.intValue++
        }
    }

    // Membuat fungsi pertanyaan sebelumnya
    //fun prevQuestion(){
    //        _currentQuestionIndex.intValue--
    //}


    // Membuat fungsi ketika mencapai pertanyaan terakhir
    fun isLastQuestion():Boolean{
        return _currentQuestionIndex.intValue >= questions.size - 1
    }

    // Membuat fungsi submitAnswer
    fun submitAnswer(answer: String) {
        _answers.value = _answers.value + Answer(currentQuestion.id, answer)
    }

    //Membuat fungsi calculate untuk kalkulasi jawaban
    fun calculateScore(): Int {
        return _answers.value.count { it.selectedAnswer == questions[it.questionId - 1].correctAnswer }
    }

    fun answersValueSize(): Int{
        return answers.value.size
    }

    fun questionSize():Int{
        return questions.size
    }

    //Membuat fungsi clearAnswers untuk membersihkan jawaban
    fun clearAnswers() {
        _answers = mutableStateOf(emptyList())
        _currentQuestionIndex.intValue = 0
    }

}