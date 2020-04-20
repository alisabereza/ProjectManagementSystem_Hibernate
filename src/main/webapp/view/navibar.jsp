<div class="navbar">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <div class="dropdown">
        <button class="dropbtn">Company
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/company/createCompany">Create Company</a>
            <a href="${pageContext.request.contextPath}/company/findCompany">Find by ID</a>
            <a href="${pageContext.request.contextPath}/company/deleteCompany">Delete Company</a>
            <a href="${pageContext.request.contextPath}/company/allCompanies">List of Companies</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Developer
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/developer/createDeveloper">Create Developer</a>
            <a href="${pageContext.request.contextPath}/developer/findDeveloper">Find by ID</a>
            <a href="${pageContext.request.contextPath}/developer/updateDeveloper">Update Developer</a>
            <a href="${pageContext.request.contextPath}/developer/deleteDeveloper">Delete Developer</a>
            <a href="${pageContext.request.contextPath}/developer/allDevelopers">List of Developers</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Project
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/project/createProject">Create Developer</a>
            <a href="${pageContext.request.contextPath}/project/findProject">Find by ID</a>
            <a href="${pageContext.request.contextPath}/project/update">Update Developer</a>
            <a href="${pageContext.request.contextPath}/project/delete">Delete Developer</a>
            <a href="${pageContext.request.contextPath}/project/allProjects">List of Developers</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Customer
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/customer/createCustomer">Create Developer</a>
            <a href="${pageContext.request.contextPath}/customer/findCustomer">Find by ID</a>
            <a href="${pageContext.request.contextPath}/customer/update">Update Developer</a>
            <a href="${pageContext.request.contextPath}/customer/delete">Delete Developer</a>
            <a href="${pageContext.request.contextPath}/customer/allCustomers">List of Developers</a>
        </div>
    </div>
</div>