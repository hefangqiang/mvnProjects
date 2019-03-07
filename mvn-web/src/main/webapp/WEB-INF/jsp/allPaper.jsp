<%--
  Created by IntelliJ IDEA.
  User: DellPC
  Date: 2018/11/8
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath = request.getContextPath(); %>
<html>
<head>
    <title>Paper列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>

                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>论文列表</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <form action="${path}/paper/queryPaperById" name="idForm" method="post">
            论文id：<input type="text" name="paperId" ><br><br>
            <input type="submit" class="btn btn-primary" value="查询id" onclick="addPaper()">&nbsp;&nbsp;
            <a class="btn btn-primary" href="${path}/paper/allPaper">查询所有</a>
            <a class="btn btn-primary" href="${path}/paper/toAddPaper">新增</a>
        </form>
    </div>
    <%--<div class="row">--%>
        <%--<div class="col-md-4 column">--%>
            <%--<a class="btn btn-primary" href="${path}/paper/toAddPaper">新增</a>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>论文编号</th>
                    <th>论文名字</th>
                    <th>论文数量</th>
                    <th>论文详情</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="paper" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${paper.paperId}</td>
                        <td>${paper.paperName}</td>
                        <td>${paper.paperNum}</td>
                        <td>${paper.paperDetail}</td>
                        <td>
                            <a href="${path}/paper/toUpdatePaper?id=${paper.paperId}">更改</a> |
                            <a href="<%=appPath%>/paper/del/${paper.paperId}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
