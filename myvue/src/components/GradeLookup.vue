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
            <el-header>
                <h1>考试成绩</h1>
            </el-header>
            <el-main>
                <div class="score">
                    <el-input v-model="searchText" placeholder="搜索" @input="handleSearch"></el-input>
                    <el-table :data="examers.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                              :header-cell-style="{background:'#D9D9D9'}" 
                               height="800" 
                               border=true
                               stripe>
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
                        <el-table-column fixed="right" label="操作" width="120">

                            <template v-slot="scope">
                                <el-button @click.prevent="deleteRow(scope.$index, tableData)" type="text" size="small">
                                    移除
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div style="text-align:center">
                        <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page = "currentPage"
                        :page-sizes="[10,20,30,40]"
                        :page-size="pageSize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="examers.length"
                        ></el-pagination>
                    </div>

                </div>
            </el-main>
        </el-container>
    </el-container>

</template>

<script>
export default {
    name: "App",
    data() {
        return {
            currentPage: 1,
            pageSize: 10,
            examers: [
                {
                    id: 1213278912,
                    name: "曹勇",
                    net: null,
                    os: 60,
                    compo: 23,
                    ds: 122
                },
                {
                    id: 21278214,
                    name: "张金亮",
                    net: 22,
                    os: null,
                    compo: 32,
                    ds: 43
                },
                {
                    id: 871236542,
                    name: "杨力",
                    net: 45,
                    os: 78,
                    compo: null,
                    ds: 87
                },
                {
                    id: 365487213,
                    name: "陈龙",
                    net: 30,
                    os: 65,
                    compo: 40,
                    ds: null
                },
                {
                    id: 984512367,
                    name: "刘华",
                    net: 55,
                    os: 70,
                    compo: 25,
                    ds: 95
                },
                {
                    id: 632587941,
                    name: "陈小红",
                    net: null,
                    os: 45,
                    compo: 30,
                    ds: 55
                },
                {
                    id: 745123698,
                    name: "赵大伟",
                    net: 20,
                    os: null,
                    compo: 50,
                    ds: 75
                },
                {
                    id: 159874236,
                    name: "黄敏",
                    net: 35,
                    os: 68,
                    compo: 42,
                    ds: null
                },
                {
                    id: 487965321,
                    name: "杨强",
                    net: 50,
                    os: 75,
                    compo: null,
                    ds: 80
                },
                {
                    id: 236587941,
                    name: "徐小龙",
                    net: null,
                    os: 55,
                    compo: 35,
                    ds: 65
                },
                {
                    id: 874123698,
                    name: "朱小强",
                    net: 25,
                    os: null,
                    compo: 60,
                    ds: 90
                },
                {
                    id: 745968321,
                    name: "孙丽丽",
                    net: 40,
                    os: 72,
                    compo: 45,
                    ds: null
                },
                {
                    id: 632587419,
                    name: "刘小杰",
                    net: 60,
                    os: 80,
                    compo: null,
                    ds: 100
                },
                {
                    id: 159874236,
                    name: "何小雪",
                    net: null,
                    os: 58,
                    compo: 48,
                    ds: 75
                },
                {
                    id: 487965832,
                    name: "王刚",
                    net: 48,
                    os: null,
                    compo: 55,
                    ds: 85
                },
                {
                    id: 236587419,
                    name: "周小红",
                    net: 32,
                    os: 70,
                    compo: 50,
                    ds: null
                },
                {
                    id: 745968532,
                    name: "刘大勇",
                    net: 55,
                    os: 78,
                    compo: null,
                    ds: 95
                },
                {
                    id: 632587149,
                    name: "李小刚",
                    net: null,
                    os: 62,
                    compo: 52,
                    ds: 80
                },
                {
                    id: 159874823,
                    name: "张小丽",
                    net: 38,
                    os: null,
                    compo: 58,
                    ds: 70
                },
                {
                    id: 487965218,
                    name: "王小华",
                    net: 42,
                    os: 73,
                    compo: 60,
                    ds: null
                },
                {
                    id: 987654321,
                    name: "刘强",
                    net: null,
                    os: 68,
                    compo: 65,
                    ds: 110
                },
                {
                    id: 654123789,
                    name: "张伟",
                    net: 18,
                    os: null,
                    compo: 45,
                    ds: 65
                },
                {
                    id: 321987654,
                    name: "王丽",
                    net: 28,
                    os: 60,
                    compo: null,
                    ds: 85
                },
                {
                    id: 741258963,
                    name: "刘敏",
                    net: 65,
                    os: 82,
                    compo: 55,
                    ds: null
                },
                {
                    id: 369258147,
                    name: "王涛",
                    net: null,
                    os: 70,
                    compo: 62,
                    ds: 90
                },
                {
                    id: 852369741,
                    name: "杨明",
                    net: 38,
                    os: null,
                    compo: 70,
                    ds: 100
                },
                {
                    id: 147852369,
                    name: "陈伟",
                    net: 50,
                    os: 75,
                    compo: null,
                    ds: 80
                },
                {
                    id: 963258741,
                    name: "李娜",
                    net: null,
                    os: 55,
                    compo: 48,
                    ds: 75
                },
                {
                    id: 654789321,
                    name: "张晓",
                    net: 45,
                    os: null,
                    compo: 55,
                    ds: 70
                },
                {
                    id: 321654987,
                    name: "王刚",
                    net: 32,
                    os: 70,
                    compo: 60,
                    ds: null
                },
                {
                    id: 987654123,
                    name: "杨梅",
                    net: 55,
                    os: 78,
                    compo: null,
                    ds: 95
                },
                {
                    id: 654123987,
                    name: "赵丽",
                    net: null,
                    os: 62,
                    compo: 52,
                    ds: 80
                },
                {
                    id: 321987654,
                    name: "陈小芳",
                    net: 38,
                    os: null,
                    compo: 58,
                    ds: 70
                },
                {
                    id: 789654321,
                    name: "孙大勇",
                    net: 42,
                    os: 73,
                    compo: 60,
                    ds: null
                },
                {
                    id: 456987321,
                    name: "李小明",
                    net: null,
                    os: 68,
                    compo: 65,
                    ds: 110
                },
                {
                    id: 123654789,
                    name: "王小伟",
                    net: 18,
                    os: null,
                    compo: 45,
                    ds: 65
                },
                {
                    id: 987321654,
                    name: "刘小丽",
                    net: 28,
                    os: 60,
                    compo: null,
                    ds: 85
                },
                {
                    id: 654987321,
                    name: "张伟",
                    net: 65,
                    os: 82,
                    compo: 55,
                    ds: null
                },
                {
                    id: 321654987,
                    name: "杨涛",
                    net: null,
                    os: 70,
                    compo: 62,
                    ds: 90
                },
                {
                    id: 987654321,
                    name: "王明",
                    net: 38,
                    os: null,
                    compo: 70,
                    ds: 100
                },
                {
                    id: 654987321,
                    name: "陈娜",
                    net: 50,
                    os: 75,
                    compo: null,
                    ds: 80
                },
                {
                    id: 321654987,
                    name: "李晓",
                    net: null,
                    os: 55,
                    compo: 48,
                    ds: 75
                },
                {
                    id: 987321654,
                    name: "王丽",
                    net: 45,
                    os: null,
                    compo: 55,
                    ds: 70
                },
                {
                    id: 654987321,
                    name: "孙刚",
                    net: 32,
                    os: 70,
                    compo: 60,
                    ds: null
                },
                {
                    id: 321654987,
                    name: "李梅",
                    net: 55,
                    os: 78,
                    compo: null,
                    ds: 95
                },
                {
                    id: 987321654,
                    name: "张丽",
                    net: null,
                    os: 62,
                    compo: 52,
                    ds: 80
                },
                {
                    id: 654987321,
                    name: "王小芳",
                    net: 38,
                    os: null,
                    compo: 58,
                    ds: 70
                },
                {
                    id: 321654987,
                    name: "孙大勇",
                    net: 42,
                    os: 73,
                    compo: 60,
                    ds: null
                },
                {
                    id: 987321654,
                    name: "陈小明",
                    net: null,
                    os: 68,
                    compo: 65,
                    ds: 110
                },
                {
                    id: 654987321,
                    name: "杨小伟",
                    net: 18,
                    os: null,
                    compo: 45,
                    ds: 65
                },
                {
                    id: 321654987,
                    name: "张小丽",
                    net: 28,
                    os: 60,
                    compo: null,
                    ds: 85
                },
                {
                    id: 987321654,
                    name: "刘伟",
                    net: 65,
                    os: 82,
                    compo: 55,
                    ds: null
                },
                {
                    id: 654987321,
                    name: "李涛",
                    net: null,
                    os: 70,
                    compo: 62,
                    ds: 90
                },
                {
                    id: 321654987,
                    name: "王明",
                    net: 38,
                    os: null,
                    compo: 70,
                    ds: 100
                },
                {
                    id: 987321654,
                    name: "杨丽",
                    net: 50,
                    os: 75,
                    compo: null,
                    ds: 80
                },
                {
                    id: 654987321,
                    name: "孙小杰",
                    net: null,
                    os: 55,
                    compo: 48,
                    ds: 75
                },
                {
                    id: 321654987,
                    name: "张小明",
                    net: 45,
                    os: null,
                    compo: 55,
                    ds: 70
                },
                {
                    id: 987321654,
                    name: "王刚",
                    net: 32,
                    os: 70,
                    compo: 60,
                    ds: null
                },
                {
                    id: 654987321,
                    name: "刘梅",
                    net: 55,
                    os: 78,
                    compo: null,
                    ds: 95
                },
                {
                    id: 321654987,
                    name: "杨丽",
                    net: null,
                    os: 62,
                    compo: 52,
                    ds: 80
                },
                {
                    id: 987321654,
                    name: "张小芳",
                    net: 38,
                    os: null,
                    compo: 58,
                    ds: 70
                },
                {
                    id: 654987321,
                    name: "王大勇",
                    net: 42,
                    os: 73,
                    compo: 60,
                    ds: null
                },
                {
                    id: 321654987,
                    name: "刘小明",
                    net: null,
                    os: 68,
                    compo: 65,
                    ds: 110
                },
                {
                    id: 987321654,
                    name: "李小伟",
                    net: 18,
                    os: null,
                    compo: 45,
                    ds: 65
                },
                {
                    id: 654987321,
                    name: "王小丽",
                    net: 28,
                    os: 60,
                    compo: null,
                    ds: 85
                },
                {
                    id: 321654987,
                    name: "陈伟",
                    net: 65,
                    os: 82,
                    compo: 55,
                    ds: null
                },
                {
                    id: 987321654,
                    name: "王涛",
                    net: null,
                    os: 70,
                    compo: 62,
                    ds: 90
                },
                {
                    id: 654987321,
                    name: "李明",
                    net: 38,
                    os: null,
                    compo: 70,
                    ds: 100
                },
                {
                    id: 321654987,
                    name: "张伟",
                    net: 50,
                    os: 75,
                    compo: null,
                    ds: 80
                },
                {
                    id: 987321654,
                    name: "李梅",
                    net: null,
                    os: 55,
                    compo: 48,
                    ds: 75
                },
                {
                    id: 654987321,
                    name: "王丽",
                    net: 45,
                    os: null,
                    compo: 55,
                    ds: 70
                },
                {
                    id: 321654987,
                    name: "刘刚",
                    net: 32,
                    os: 70,
                    compo: 60,
                    ds: null
                },
                {
                    id: 987321654,
                    name: "王梅",
                    net: 55,
                    os: 78,
                    compo: null,
                    ds: 95
                },
                {
                    id: 654987321,
                    name: "陈丽",
                    net: null,
                    os: 62,
                    compo: 52,
                    ds: 80
                },
                {
                    id: 321654987,
                    name: "张小伟",
                    net: 38,
                    os: null,
                    compo: 58,
                    ds: 70
                },
                {
                    id: 987321654,
                    name: "李大勇",
                    net: 42,
                    os: 73,
                    compo: 60,
                    ds: null
                },
                {
                    id: 654987321,
                    name: "孙小明",
                    net: null,
                    os: 68,
                    compo: 65,
                    ds: 110
                },
                {
                    id: 321654987,
                    name: "张小丽",
                    net: 18,
                    os: null,
                    compo: 45,
                    ds: 65
                },
                {
                    id: 987321654,
                    name: "王小伟",
                    net: 28,
                    os: 60,
                    compo: null,
                    ds: 85
                },
                {
                    id: 654987321,
                    name: "刘伟",
                    net: 65,
                    os: 82,
                    compo: 55,
                    ds: null
                },
                {
                    id: 321654987,
                    name: "李涛",
                    net: null,
                    os: 70,
                    compo: 62,
                    ds: 90
                },
                {
                    id: 987321654,
                    name: "张明",
                    net: 38,
                    os: null,
                    compo: 70,
                    ds: 100
                },
                {
                    id: 654987321,
                    name: "刘丽",
                    net: 50,
                    os: 75,
                    compo: null,
                    ds: 80
                },
                {
                    id: 321654987,
                    name: "杨小杰",
                    net: null,
                    os: 55,
                    compo: 48,
                    ds: 75
                },
                {
                    id: 987321654,
                    name: "王小明",
                    net: 45,
                    os: null,
                    compo: 55,
                    ds: 70
                },
                {
                    id: 654987321,
                    name: "孙刚",
                    net: 32,
                    os: 70,
                    compo: 60,
                    ds: null
                },
                {
                    id: 321654987,
                    name: "李梅",
                    net: 55,
                    os: 78,
                    compo: null,
                    ds: 95
                },
                {
                    id: 987321654,
                    name: "张丽",
                    net: null,
                    os: 62,
                    compo: 52,
                    ds: 80
                },
                {
                    id: 654987321,
                    name: "王小芳",
                    net: 38,
                    os: null,
                    compo: 58,
                    ds: 70
                },
                {
                    id: 321654987,
                    name: "刘大勇",
                    net: 42,
                    os: 73,
                    compo: 60,
                    ds: null
                },
                {
                    id: 987321654,
                    name: "杨小明",
                    net: null,
                    os: 68,
                    compo: 65,
                    ds: 110
                },
                {
                    id: 654987321,
                    name: "王小丽",
                    net: 18,
                    os: null,
                    compo: 45,
                    ds: 65
                },
                {
                    id: 321654987,
                    name: "陈伟",
                    net: 28,
                    os: 60,
                    compo: null,
                    ds: 85
                },
                {
                    id: 987321654,
                    name: "刘伟",
                    net: 65,
                    os: 82,
                    compo: 55,
                    ds: null
                },
                {
                    id: 654987321,
                    name: "李涛",
                    net: null,
                    os: 70,
                    compo: 62,
                    ds: 90
                },
                {
                    id: 321654987,
                    name: "张伟",
                    net: 38,
                    os: null,
                    compo: 70,
                    ds: 100
                },
                {
                    id: 987321654,
                    name: "李梅",
                    net: 50,
                    os: 75,
                    compo: null,
                    ds: 80
                },
                {
                    id: 654987321,
                    name: "王小杰",
                    net: null,
                    os: 55,
                    compo: 48,
                    ds: 75
                },
                {
                    id: 321654987,
                    name: "杨小明",
                    net: 45,
                    os: null,
                    compo: 55,
                    ds: 70
                },
                {
                    id: 987321654,
                    name: "王刚",
                    net: 32,
                    os: 70,
                    compo: 60,
                    ds: null
                },
                {
                    id: 654987321,
                    name: "刘梅",
                    net: 55,
                    os: 78,
                    compo: null,
                    ds: 95
                },
                {
                    id: 321654987,
                    name: "杨丽",
                    net: null,
                    os: 62,
                    compo: 52,
                    ds: 80
                },
                {
                    id: 987321654,
                    name: "张小芳",
                    net: 38,
                    os: null,
                    compo: 58,
                    ds: 70
                },
                {
                    id: 654987321,
                    name: "王大勇",
                    net: 42,
                    os: 73,
                    compo: 60,
                    ds: null
                },
                {
                    id: 321654987,
                    name: "刘小明",
                    net: null,
                    os: 68,
                    compo: 65,
                    ds: 110
                },
                {
                    id: 987321654,
                    name: "李小伟",
                    net: 18,
                    os: null,
                    compo: 45,
                    ds: 65
                },
                {
                    id: 654987321,
                    name: "王小丽",
                    net: 28,
                    os: 60,
                    compo: null,
                    ds: 85
                },
                {
                    id: 321654987,
                    name: "陈伟",
                    net: 65,
                    os: 82,
                    compo: 55,
                    ds: null
                },
                {
                    id: 987321654,
                    name: "王涛",
                    net: null,
                    os: 70,
                    compo: 62,
                    ds: 90
                },
                {
                    id: 654987321,
                    name: "李明",
                    net: 38,
                    os: null,
                    compo: 70,
                    ds: 100
                },
                {
                    id: 321654987,
                    name: "张伟",
                    net: 50,
                    os: 75,
                    compo: null,
                    ds: 80
                },
                {
                    id: 987321654,
                    name: "李梅",
                    net: null,
                    os: 55,
                    compo: 48,
                    ds: 75
                },
                {
                    id: 654987321,
                    name: "王丽",
                    net: 45,
                    os: null,
                    compo: 55,
                    ds: 70
                },
                {
                    id: 321654987,
                    name: "刘刚",
                    net: 32,
                    os: 70,
                    compo: 60,
                    ds: null
                },
                {
                    id: 987321654,
                    name: "王梅",
                    net: 55,
                    os: 78,
                    compo: null,
                    ds: 95
                },
                {
                    id: 654987321,
                    name: "陈丽",
                    net: null,
                    os: 62,
                    compo: 52,
                    ds: 80
                },
                {
                    id: 321654987,
                    name: "张小伟",
                    net: 38,
                    os: null,
                    compo: 58,
                    ds: 70
                },
                {
                    id: 987321654,
                    name: "李大勇",
                    net: 42,
                    os: 73,
                    compo: 60,
                    ds: null
                },
                {
                    id: 654987321,
                    name: "孙小明",
                    net: null,
                    os: 68,
                    compo: 65,
                    ds: 110
                },
                {
                    id: 321654987,
                    name: "张小丽",
                    net: 18,
                    os: null,
                    compo: 45,
                    ds: 65
                },
                {
                    id: 987321654,
                    name: "王小伟",
                    net: 28,
                    os: 60,
                    compo: null,
                    ds: 85
                },
                {
                    id: 654987321,
                    name: "刘伟",
                    net: 65,
                    os: 82,
                    compo: 55,
                    ds: null
                },
                {
                    id: 321654987,
                    name: "李涛",
                    net: null,
                    os: 70,
                    compo: 62,
                    ds: 90
                },
                {
                    id: 987321654,
                    name: "张伟",
                    net: 38,
                    os: null,
                    compo: 70,
                    ds: 100
                },
                {
                    id: 654987321,
                    name: "李梅",
                    net: 50,
                    os: 75,
                    compo: null,
                    ds: 80
                },
                {
                    id: 321654987,
                    name: "王小杰",
                    net: null,
                    os: 55,
                    compo: 48,
                    ds: 75
                },
                {
                    id: 987321654,
                    name: "杨小明",
                    net: 45,
                    os: null,
                    compo: 55,
                    ds: 70
                },
                {
                    id: 654987321,
                    name: "王刚",
                    net: 32,
                    os: 70,
                    compo: 60,
                    ds: null
                },
                {
                    id: 321654987,
                    name: "刘梅",
                    net: 55,
                    os: 78,
                    compo: null,
                    ds: 95
                },
                {
                    id: 987321654,
                    name: "李丽",
                    net: null,
                    os: 62,
                    compo: 52,
                    ds: 80
                },
                {
                    id: 654987321,
                    name: "王小芳",
                    net: 38,
                    os: null,
                    compo: 58,
                    ds: 70
                },
                {
                    id: 321654987,
                    name: "刘大勇",
                    net: 42,
                    os: 73,
                    compo: 60,
                    ds: null
                },
                {
                    id: 987321654,
                    name: "杨小明",
                    net: null,
                    os: 68,
                    compo: 65,
                    ds: 110
                },
                {
                    id: 654987321,
                    name: "王小丽",
                    net: 18,
                    os: null,
                    compo: 45,
                    ds: 65
                },
                {
                    id: 321654987,
                    name: "陈伟",
                    net: 28,
                    os: 60,
                    compo: null,
                    ds: 85
                }],
            searchText: '',
            // tableData: [
            //     { name: '张三', age: 30, gender: '男' },
            //     { name: '李四', age: 25, gender: '女' },
            //     { name: '王五', age: 35, gender: '男' },
            //     // 更多数据...
            // ]
        };

    },

    computed: {
        filteredData() {
            return this.examers.filter(item => {
                // 在姓名、年龄、性别中搜索匹配的数据
                return Object.values(item).some(value => {
                    return String(value).toLowerCase().includes(this.searchText.toLowerCase());
                });
            });
        }
    },
    methods: {
        handleSizeChange(val) {
            this.pageSize = val;
        },
        handleCurrentChange(val) {
            this.currentPage = val;
        },
        Togradelookup() {
            this.$router.push('/gradelookup');
        },
        logout() {
            this.$router.push('/');
        },
        handleSearch(value) {
            this.searchText = value;
        }
    }

};
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
    margin-left: 100px;
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

/* 
.buttons button {
    width: 120px;
    height: 60px;
    border: none;
    border-radius: 15px;
    color: white;
    font-size: 20px;
} */

.red {
    background-color: red;
}

.green {
    background-color: green;
}

.purple {
    background-color: purple;
}


.el-table th {
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


.el-table td {
    padding: 10px;
    border: 1px solid #0c090977;
    text-align: center;
    background-color: #F8F9FB;
    width: 176px;
    width: 100px;
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