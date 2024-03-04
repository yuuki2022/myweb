<template>
    <div id="app">
        <div class="leftbar">
            <div class="header">
                <span class="username">10086</span>
                <hr>
                <button class="logout">退出</button>
            </div>
            <hr>
            <div class="buttons">
                <button class="red">出试卷</button>
                <br>
                <button @click="Togradelookup" class="green">查询成绩</button>
                <br>
                <!-- <button class="purple">检查结果</button> -->
            </div>
        </div>
        <!-- <table class="table">
            <thead>
                <tr>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>科室</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="patient in patients" :key="patient.id">
                    <td>{{ patient.name }}</td>
                    <td>{{ patient.gender }}</td>
                    <td>{{ patient.age }}</td>
                    <td>{{ patient.department }}</td>
                </tr>
            </tbody>
        </table>
        <button class="confirm">确认</button> -->
    </div>
</template>

<script>

export default {
    name: "App",
    data() {
        return {
            patients: [
                {
                    id: 1213278912,
                    name: "韩男",
                    gender: "男",
                    age: 69,
                    department: "妇科",
                },
                {
                    id: 21278214,
                    name: "张女士",
                    gender: "女",
                    age: 99,
                    department: "大连退",
                },
            ],
        };
    },
    methods:{
        Togradelookup(){
            this.$router.push('/login');
        }
    }
};
</script>

<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

.header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 20px;
    display: flex;
    flex-direction: column;

}

.username {
    font-size: 24px;
    font-weight: bold;
    padding: 10px;
}

.logout {
    margin-top: 10px;
    width: 80px;
    height: 40px;
    border: none;
    background-color: red;
    color: white;
    font-size: 18px;
}

.buttons {
    display: flex;
    align-items: center;
    justify-content: space-around;
    margin: 20px;
    flex-direction: column;

}

.buttons button {
    width: 120px;
    height: 60px;
    border: none;
    border-radius: 15px;
    color: white;
    font-size: 20px;
}

.red {
    background-color: red;
}

.green {
    background-color: green;
}

.purple {
    background-color: purple;
}

.table {
    width: 80%;
    margin: 0 auto;
    border-collapse: collapse;
}

.table th,
.table td {
    padding: 10px;
    border: 1px solid black;
    text-align: center;
}

.confirm {
    width: 100px;
    height: 50px;
    border: none;
    border-radius: 10px;
    background-color: green;
    color: white;
    font-size: 18px;
    margin: 20px auto;
    display: block;
}

.leftbar {
    height: 100%;
    position: fixed;
    left: 0px;
    top: 0px;
    flex-direction: column;
    background-color: #f0f0f0;

}
</style>