<template>
    <div id="Student">
        <div class="header">
            <div class="title">选择科目</div>
        </div>

        <div class="student-info">
            <p style="font-size: 20px;">学号</p>
            <p id="studentId"> 123456789</p>
            <button id="exit-button">退出</button>
        </div>

        <div class="subject-list">
            <div class="subject-item">
                <span style="font-size: 25px;">计算机网络</span>
                <button class="exam-button" style="font-size: 15px;">参加考试</button>
            </div>
            <div class="subject-item">
                <span style="font-size: 25px;">计算机操作系统</span>
                <button class="exam-button" style="font-size: 15px;">参加考试</button>
            </div>
            <div class="subject-item">
                <span style="font-size: 25px;">计算机组成原理</span>
                <button class="exam-button" style="font-size: 15px;">参加考试</button>
            </div>
            <div class="subject-item">
                <span style="font-size: 25px;">数据结构</span>
                <button class="exam-button" style="font-size: 15px;">参加考试</button>
            </div>
        </div>
    </div>
</template>



<script>
export default {
    name: 'StudentComponent',
    data() {
        return {
            studentId: ''
        }
    },
    methods: {
        exit() {
            this.$router.push('/')
        }
    }
}
</script>


<style scoped>
p{
    /*取消p标签的默认样式*/
    margin: 0;
    padding: 0;
    font-size: 20px;
    font-weight: bold;
}

#studentId{
    margin-top: 5%;
}

.header {
    text-align: center;
    padding: 20px;
    background-color: #FFFFFF;
    margin-left: 80%;
}

.title {
    font-size: 80px;
    font-weight: bold;
    margin-left:80%;
    width: 300%;

}

.student-info {
    background-color: #F2F6FE;
    width: 10%;
    position: fixed;
    padding: 1%;
    left:2%;
    top: 10%;
    display: flex;
    flex-direction: column;
    align-items: center;
}

#exit-button {
    top: 100%;
    margin-top: 10px;
    background-color: red;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    width: 80%;
}

.subject-list {
    margin-top: 10%;
    width: 100%;
    margin-left: 50%;
    margin-right: auto;
    border-radius: 25px;
    text-align: center;
    font-weight: bold;
    height: 120%;
    display: flex;
    flex-direction: column;
}

.subject-item {
    margin: 20px;
    height: 60px;
    padding: 30px;
    border: 2px;
    border-radius: 25px;;
    background-color: #F8F9FB;
    /* 文字垂直居中*/
    box-sizing: border-box;
    height: 50%;
    padding-top: 3%;
}

.exam-button {
    background-color:#40CD70;
    color: white;
    padding: 10px 5px;
    border: none;
    cursor: pointer;
    float: right;
}
</style>