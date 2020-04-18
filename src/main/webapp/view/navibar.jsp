<div class="navbar">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <div class="dropdown">
        <button class="dropbtn">Company
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/company/createCompany">Create Company</a>
            <a href="${pageContext.request.contextPath}/company/findCompany">Find by ID</a>
            <a href="${pageContext.request.contextPath}/company/update">Update Company</a>
            <a href="${pageContext.request.contextPath}/company/delete">Delete Company</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Developer
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/developer/createDeveloper">Create Developer</a>
            <a href="${pageContext.request.contextPath}/developer/findDeveloper">Find by ID</a>
            <a href="${pageContext.request.contextPath}/developer/update">Update Developer</a>
            <a href="${pageContext.request.contextPath}/developer/delete">Delete Developer</a>
        </div>
    </div>
</div>