<template>
    <el-container>
        <el-aside width=150px>
            <div class="username">10086</div>
            <!-- <button @click="logout" class="logout">退出</button> -->
            <el-button type="danger" @click="$root.logout" size="large" id="quit">退出</el-button>
            <hr>
            <div class="buttons">
                <el-button type="primary" @click="$root.CreateQuestion" size="large" id="createPaper">出试卷</el-button>
                <br>
                <el-button type="primary" @click="$root.Togradelookup" size="large" id="lookgrade">查询成绩</el-button>
                <br>
                <el-button type="primary" size="large" id="organizeStudent">管理学生</el-button>
                <br>
                <!-- <button class="purple">检查结果</button> -->
            </div>
        </el-aside>

        <el-container>

            <el-header>
                <h1>出试卷</h1>
            </el-header>
            <el-main>

                <el-form label-width="80px">
                    <div>
                        <el-select v-model="value" placeholder="选择科目" class="chooseSubject" style="width: 180px">
                            <el-option v-for="item in options" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-select>
                    </div>
                    <p style="display: block; font-weight: bold; font-size: 30px;">输入题目</p>
                    <el-input id="textarea" v-model="question" style="width: 100%" :rows="3" type="textarea"
                        placeholder="输入题目" />
                    <p style="font-weight: 400; font-weight: bold; font-size: 30px;">输入选项</p>
                    <el-form-item label="选项A">
                        <el-input v-model="optionA" placeholder="请输入选项A"></el-input>
                    </el-form-item>
                    <el-form-item label="选项B">
                        <el-input v-model="optionB" placeholder="请输入选项B"></el-input>
                    </el-form-item>
                    <el-form-item label="选项C">
                        <el-input v-model="optionC" placeholder="请输入选项C"></el-input>
                    </el-form-item>
                    <el-form-item label="选项D">
                        <el-input v-model="optionD" placeholder="请输入选项D"></el-input>
                    </el-form-item>
                    <el-form-item label="正确答案">
                        <el-radio-group v-model="answer">
                            <el-radio :value=0>A</el-radio>
                            <el-radio :value=1>B</el-radio>
                            <el-radio :value=2>C</el-radio>
                            <el-radio :value=3>D</el-radio>

                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="难度等级">
                        <el-radio-group v-model="difficulty">
                            <el-radio :value=0>简单</el-radio>
                            <el-radio :value=1>中等</el-radio>
                            <el-radio :value=2>困难</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-button type="success" class="save" size="large" round>保存</el-button>

                </el-form>
            </el-main>
        </el-container>
    </el-container>




</template>

<script>
export default {
    name: 'CreateQuestionComponent',
    data() {
        return {
            question: '',
            optionA: '',
            optionB: '',
            optionC: '',
            optionD: '',
            answer: ''
        }
    },
    methods: {


    }
}</script>

<script setup>

import { ref } from 'vue'

const value = ref('')

const options = [
    {
        value: 'net',
        label: '计算机网络',
    },
    {
        value: 'os',
        label: '计算机操作系统',
    },
    {
        value: 'compo',
        label: '计算机组成原理',
    },
    {
        value: 'ds',
        label: '数据结构',
    },

]

const answer = ref('1')

const difficulty = ref('1')

const question = ref('')

const optionA = ref('')
const optionB = ref('')
const optionC = ref('')
const optionD = ref('')

</script>

<style scoped>
p {
    text-align: left;
}

#question {
    text-align: left;

}

.save {
    background-color: #43CD72;
}

.el-form-item {
    margin-bottom: 20px;
}

.el-form-item .el-form-item__label {
    border-radius: 50%;
    font-size: 20px;
    font-weight: bold;
    background-color: #D9D9D9;
    width: 50px;
    height: 30px;
    display: inline-block;
    line-height: 100%;
}

.chooseSubject {
    position: relative;
    left: -43%;
    font-size: 20px;
    font-weight: bold;
}

.el-aside {
    height: 100%;
    position: fixed;
    left: 0px;
    top: 0px;
    flex-direction: column;
    background-color: #f0f0f0;
}

.el-main {
    width: 1100px;
    height:auto;
    left: 409px;
    border-radius: 15px;
    background-color: #F8F9FB;
}

textarea {
    width: 80%;
    height: 100px;
    border-radius: 10px;
    border: 1px solid #000;
    display: block;
    text-align: center;
    /*设置字体大小 */
    font-size: 20px;
    margin-bottom: 3%;

}
</style>
