<template>
    <el-container>
        <el-aside width=150px>
            <div class="username">10086</div>
            <!-- <button @click="logout" class="logout">退出</button> -->
            <el-button type="danger" @click="logout" size="large" id="quit">退出</el-button>
            <hr>
            <div class="buttons">
                <el-button type="primary" size="large" id="createPaper">出试卷</el-button>
                <br>
                <el-button type="primary" @click="Togradelookup" size="large" id="lookgrade">查询成绩</el-button>
                <br>
                <el-button type="primary" @click="Togradelookup" size="large" id="organizeStudent">管理学生</el-button>
                <br>
                <!-- <button class="purple">检查结果</button> -->
            </div>
        </el-aside>

        <el-container>

            <el-main>

                <el-form label-width="80px">
                    <div>
                        <select class="chooseSubject">
                            <option value="1">计算机网络</option>
                            <option value="2">计算机组成原理</option>
                            <option value="3">计算机操作系统</option>
                            <option value="4">数据结构</option>
                        </select>
                    </div>
                    <p style="display: block; font-weight: bold; font-size: 30px;">输入题目</p>
                    <textarea id="question"></textarea>
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
                    <el-form-item label="答案">
                        <el-input v-model="answer" placeholder="请输入答案"></el-input>
                    </el-form-item>
                    <el-form-item label="难度等级">
                        <el-radio-group v-model="radio">
                            <el-radio :value=0>困难</el-radio>
                            <el-radio :value=1>中等</el-radio>
                            <el-radio :value=2>容易</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <button class="save">保存</button>
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
}

</script>

<style scoped>
p {
    text-align: left;
}
#question{
    text-align: left;
    
}
.save{
    background-color: #43CD72;
    color:#FFF;
    width: 100px;
    height: 60px;
    border-radius: 10px;
}
.el-form-item{
    margin-bottom: 20px;
    height: 50px;
}
.el-form-item .el-form-item__label {
    border-radius: 50%;
    font-size: 20px;
    font-weight: bold;
    background-color: #D9D9D9;
    width: 50px;
    height: 20px;
    display: inline-block;
    line-height: 100%;
}

.chooseSubject {
    position: relative;
    left: -45%;
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
    height: 880px;
    margin-top: 5%;
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
