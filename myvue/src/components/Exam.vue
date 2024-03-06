<template>
  <div id="test">
    <el-row>
      <el-col :span="3" :offset="1">
        <el-row gutter="20">
          <div class="msgDisplay" style="height:140px">
            <el-col>
              <!-- <h2 style="margin-bottom: 15px;">考生</h2> -->
              <h2 style="margin-bottom: 5px;">{{ studentName }}</h2>
              <h4>{{ studentId }}</h4>
            </el-col>

          </div>
        </el-row>
        <el-row gutter="20">
          <div class="buttonContainer buttons">
            <el-scrollbar height="400px">
              <br>
              <el-button v-for="(question, index) in questions" :key="question.questionId"
                @click="scrollToQuestion(question.questionId)" circle size="mini"
                :class="{ 'selected': question.choice !== null }">{{ index + 1 }}</el-button>
            </el-scrollbar>
          </div>
        </el-row>
        <el-row class="rowCenter">
          <div class="buttonContainer">
            <el-button type="success" size="medium" style="text-align:center">提交</el-button>
          </div>
        </el-row>
      </el-col>

      <el-col :span="14" :offset="1">
        <el-row gutter="20">
          <div>
            <el-scrollbar height="650px">
              <el-card class="course" style="height:80%">
                <h1>{{ courseName }}</h1>
              </el-card>
              <el-card v-for="(question, index) in questions" :key="question.questionId" class="questionCard"
                :id="'question-' + question.questionId">
                <a>{{ index + 1 }}.{{ question.content }}</a>
                <br>
                <el-radio-group v-model="question.choice" class="ml-4">
                  <el-radio value="1" size="large">A.{{ question.answer[0] }}</el-radio>
                  <el-radio value="2" size="large">B.{{ question.answer[1] }}</el-radio>
                  <el-radio value="3" size="large">C.{{ question.answer[2] }}</el-radio>
                  <el-radio value="4" size="large">D.{{ question.answer[3] }}</el-radio>
                </el-radio-group>
              </el-card>
            </el-scrollbar>
          </div>
        </el-row>
      </el-col>
      <!--倒计时和草稿-->
      <el-col :span="3" :offset="1" style="text-align: center;">
        <el-row gutter="20">
          <!--el-card shadow="hover" style="width: 100%;margin-top: 20px; display: flex; flex-direction: column; align-items: center; height:80%"-->
          <div
            style="width: 100%; background: #F8F9FB; height:140px ;display: flex;flex-direction: column; align-items: center;">

            <el-button style="margin-top:10px;" class="countdown-footer" type="primary" @click="visible = !visible">{{ visible ? '隐藏' : '显示'
              }}考试剩余时间</el-button>

            <h3 style="color: red; margin-bottom: 0px;" v-show="visible">考试剩余时间</h3>
            <el-countdown v-show="visible" format="HH:mm:ss" :value="timer"
              value-style="color:red ; font-size:40px ;font-weight:bold" />

          </div>
          <!--/el-card-->
        </el-row>
        <el-row>
          <textarea placeholder="草稿区域" class="text" wrap="soft"></textarea>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from 'axios'
import questionData from '../../../文档/jsons/questions.json'
export default {
  name: 'ExamComponent',
  data() {
    return {
      studentId: questionData.studentId,
      course_id: questionData.course_id,
      studentName: questionData.studentName,
      courseName: "sb",
      visible: true,
      questions: questionData.questions.map(question => ({
        ...question,
        choice: null // 添加 choice 属性，默认值为 null
      })),
      timer: Date.now() + 1000 * 60 * 60 * 24 * 2
    }
  },
  methods: {
    scrollToQuestion(questionId) {
      // 获取对应的题目元素
      const questionElement = document.getElementById('question-' + questionId);
      if (questionElement) {
        // 滚动到题目元素的位置
        questionElement.scrollIntoView({ behavior: 'smooth', block: 'center' });
      }
    },
    SubmitEvent() {

    },
    fetchQuestions() {
      axios.get("./文档/jsons/questions.json")
        .then(response => {
          // 解析JSON数据
          this.studentId = response.data.studentId;
          this.course_id = response.data.course_id;
          this.questions = response.data.questions;
          console.log();
        })
        .catch(error => {
          console.error('Error fetching questions:', error);
        });
    }
  },
  mounted() {
    //this.fetchQuestions();
  }


}
</script>



<style scoped>
#test {
  width: 180%;
  height: 100%;
  margin-top: 5%;
}

.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.msgDisplay {
  background: #F2F6FE;
  text-align: center;
  width: 250px;

}

.course {
  background: #F8F9FB;
  text-align: center;
}

.text {
  background: #FFFDCF;
  height: 450px;
  width: 233px;
  resize: none;
  border: none;
}

.p {
  text-align: center;
  align-self: center;
}

.el-countdown {
  visibility: hidden;
  /* 使用 visibility 隐藏元素 */
}

.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}

.buttons {
  display: flex;
  justify-content: center;
  /* 水平居中对齐 */
  align-items: center;
  /* 垂直居中对齐 */
  background: #F2F6FE;
}

.button-container {
  display: flex;
  flex-direction: column;
  gap: 40px;
  /* 设置按钮之间的垂直间距 */
}

.button-container>* {
  flex: 1;
  /* 让每个按钮在垂直方向上等分剩余空间 */
}

.el-button {
  width: 150px;
  margin: 0 5px;
  /* 设置按钮之间的水平间距 */
}

.questionCard {
  margin: 15px;
}

.rowCenter {
  display: flex;
  justify-content: center;
  /* 水平居中 */
}

.selected {
  background-color: #409EFF;
  color: #fff;
}
</style>