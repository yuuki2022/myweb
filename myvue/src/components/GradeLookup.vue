<template>
    <el-container>
        <el-aside width=150px>
            <div class="username">{{ username }}</div>
            <!-- <button @click="logout" class="logout">退出</button> -->
            <el-button type="danger" @click="$root.logout" size="large" id="quit">退出</el-button>
            <hr>
            <div class="buttons">
                <el-button type="primary" size="large" @click="$root.CreateQuestion" id="createPaper">出试卷</el-button>
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
                <h1>考试成绩</h1>
            </el-header>
            <el-main>
                <div class="score">

                    <div style="display: flex; align-items: center;">
                        <el-input v-model="searchText" placeholder="搜索" @input="handleSearch" size="mini"></el-input>
                        <el-button type="danger" style="width: fit-content;" @click="formVisible = true"
                            size="mini">插入一条学生数据</el-button>
                    </div>


                    <el-table :data="examers.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                        :header-cell-style="{ background: '#D9D9D9' }" border=true stripe>
                        <!-- 表格列 -->
                        <el-table-column prop="id" label="考生号" class="id"></el-table-column>
                        <el-table-column prop="name" label="姓名 "></el-table-column>
                        <el-table-column prop="net" label="计算机网络">
                            <template #default="{ row }">
                                {{ row.net || '未参加考试' }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="os" label="计算机操作系统">

                            <template #default="{ row }">
                                {{ row.os || '未参加考试' }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="compo" label="计算机组成原理">

                            <template #default="{ row }">
                                {{ row.compo || '未参加考试' }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="ds" label="数据结构">

                            <template #default="{ row }">
                                {{ row.ds || '未参加考试' }}
                            </template>
                        </el-table-column>
                        <el-table-column fixed="right" label="操作">

                            <template v-slot="scope">
                                <el-button @click.prevent="deleteRow(scope.$index, examers)" type="danger" size="small">
                                    移除
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div style="text-align:center">
                        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                            :current-page="currentPage" :page-sizes="[10, 20, 30, 40]" :page-size="pageSize"
                            layout="total, sizes, prev, pager, next, jumper" :total="examers.length"></el-pagination>
                    </div>

                </div>
            </el-main>
        </el-container>

        <el-dialog v-model="formVisible" title="添加一条学生数据" width="500">
            <el-form :model="form">
                <el-form-item label="学号" :label-width="formLabelWidth">
                    <el-input v-model="form.studentId" autocomplete="off" />
                </el-form-item>
                <el-form-item label="姓名" :label-width="formLabelWidth">
                    <el-input v-model="form.studentName" autocomplete="off" />
                </el-form-item>
            </el-form>

            <template #footer>
                <div class="dialog-footer">
                    <el-button type="primary" @click="formVisible = false">取消</el-button>
                    <el-button type="danger" @click="addStudentData">确认</el-button>
                </div>
            </template>
        </el-dialog>

        <el-dialog v-model="visible" :show-close="false" width="500">

            <template #header="{ close, titleId, titleClass }">
                <div class="my-header">
                    <h4 :id="titleId" :class="titleClass">您确认需要删除这个学生的信息吗？</h4>
                    <el-button type="danger" @click="confirmDelete">
                        <el-icon class="el-icon--left">
                            <CircleCloseFilled />
                        </el-icon>
                    确认
                    </el-button>
                    <el-button type="primary" @click="close">
                        <el-icon class="el-icon--left">
                            <CircleCloseFilled />
                        </el-icon>
                    关闭
                    </el-button>
                </div>
            </template>
        </el-dialog>

    </el-container>

</template>

<script>
import axios from 'axios';
import { useStore } from 'vuex';
import { ElMessage } from 'element-plus';
import CryptoJS from 'crypto-js';
export default {
    name: "App",
    data() {
        const store = useStore();
        return {
            store, //vuex
            currentPage: 1,
            pageSize: 10,
            examers: [],
            searchText: '',
            username: store.state.userName,
            visible: false,
            confirm: false,
            deleteIndex: '',
            deleteRows: '',
            formVisible: false,
            form: {
                studentId: '',
                studentName: '',
            },
        };

    },
    created() {
        this.getStudentsData();
        console.log(this.store.state.path)
        console.log(`${this.store.state.path}students`)
        console.log(this.store.state.userName)

    },

    computed: {

    },
    methods: {
        handleSizeChange(val) {
            this.pageSize = val;
        },
        handleCurrentChange(val) {
            this.currentPage = val;
        },
        handleSearch(value) {
            this.searchText = value;
        },
        getStudentsData() {
            axios.get(`${this.store.state.path}students`, {
            }).then(res => {
                console.log("res", res.data['data'])
                let student = res.data['data']
                console.log(student)
                this.examers = student
            })
        },
        deleteRow(index, rows) {
            this.deleteIndex = index;
            this.deleteRows = rows;
            this.visible = true;
        },
        confirmDelete() {
            this.visible = false;
            axios.delete(this.store.state.path+'student/delete', {
                params: {
                    studentId: this.deleteRows[this.deleteIndex].id
                }
            }).then(res => {
                console.log("res", res)
                this.getStudentsData()
            })
        },
        addStudentData() {
            this.formVisible = false
            console.log(this.form.studentId)
            console.log(this.form.studentName)
            axios.get(this.store.state.path+'student/insert', {
                params: {
                    studentId: this.form.studentId,
                    saltPassword: CryptoJS.SHA256("123456").toString(),
                    studentName: this.form.studentName,
                }
            }).then(res => {
                console.log("res", res)
                this.getStudentsData()
                this.formVisible = false
                ElMessage.success('已经成功添加一条学生数据')
            })
        }

    }
}


</script>


<!-- 

<script lang="ts">
import type { TableColumnCtx } from 'element-plus'

interface User {
  id: int
  name: string
  net: string
  os: string
  compo:string
  ds:string

}

</script> -->

<style>
/* * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
} */
.buttons {
    display: flex;
    flex-direction: column;
    /*元素之间不要留空隙 */
    /* 设置元素居中 */

}

.el-container {
    margin-left: 5%;
}

/* #createPaper {
    width: 150%;
    height: 80px;
    background-color: #AD0000;
    color: #000000;
    font-size: 30px;
}

#organizeStudent {
    width: 150%;
    height: 80px;
    background-color: #BD73E0;
    color: #FFFFFF;
    font-size: 30px;
}

#lookgrade {
    margin-top: 0;
    width: 150%;
    height: 80px;
    background-color: #67C3E1;
    color: #000000;
    font-size: 30px;
} */

#quit {
    width: 50%;
    height: 40px;
    background-color: #FF2424;

}

.el-button {
    width: 100px;
    text-align: center;
}

.el-container {
    width: 180%;
}

.title {
    margin-top: 30px;
    text-align: center;
    /* Center the text horizontally */
}

.el-header {
    /* margin-left: 50%; */
    size: 40pt;
}


.header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 20px;
    display: flex;
    flex-direction: column;

}

.el-main {
    width: 90%;
    margin-left: 5%;
    border-collapse: collapse;
}

/*设置el-table第一列的样式*/

.username {
    font-size: 24px;
    font-weight: bold;
    padding: 20px;
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

.el-button {
    width: 100px;
}

/* 
.buttons button {
    width: 120px;
    height: 60px;
    border: none;
    border-radius: 15px;
    color: white;
    font-size: 20px;
} */

.el-table th {
    width: 176px;
    height: 80px;
    padding: 10px;
    border: 1px solid #0c090977;
    text-align: center;
    background-color: #D9D9D9;
    width: 176px;
}

.id {
    width: 100px;
    background-color: #D9D9D9;
}

.el-table th {
    width: 176px;
    height: 80px;
}

.el-table td {
    padding: 10px;
    border: 1px solid #0c090977;
    text-align: center;
    background-color: #F8F9FB;
    width: 176px;
    height: 60px;
}

.el-table td>.cell {
    text-align: center;
}

.el-table th>.cell {
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

.el-aside {
    height: 100%;
    position: fixed;
    left: 0px;
    top: 0px;
    flex-direction: column;
    background-color: #f0f0f0;

}

.el-table-column {
    width: 90px;

}
</style>