<template>
    <div id="Student">
        <div class="header">
            <div class="title">选择科目</div>
        </div>

        <div class="student-info">
            <span style="font-size: 20px;">学号<br></span>
            <span id="studentId"> 123456789</span>
            <button class="exit-button">退出</button>
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
}
</script>


<style scoped>
body {
    font-family: Arial, sans-serif;
}

.header {
    text-align: center;
    padding: 20px;
    background-color: #f0f0f0;
    width: 100%;
}

.title {
    font-size: 24px;
}

.student-info {
    margin-top: 20px;
    margin-left: 20px;
    position: relative;
}

.exit-button {
    position: absolute;
    left: 0;
    top: 100%;
    margin-top: 10px;
    background-color: red;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
}

.subject-list {
    margin-top: 50px;
    width: 30%;
    margin-left: auto;
    margin-right: auto;
    border: 2px solid;
    border-radius: 25px;
    text-align: center;
}

.subject-item {
    margin: 20px;
    padding: 20px;
    border: 2px solid;
    border-radius: 25px;
}

.exam-button {
    float: right;
    background-color: green;
    color: white;
    padding: 10px 5px;
    border: none;
    cursor: pointer;
}
</style>