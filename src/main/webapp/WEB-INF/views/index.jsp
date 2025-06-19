<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Horse Feed Service - Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .hero-section {
            background: linear-gradient(135deg, #28a745 0%, #20c997 100%);
            color: white;
            padding: 60px 0;
            margin: -30px -15px 40px -15px;
        }
        .stats-card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
            transition: transform 0.2s;
        }
        .stats-card:hover {
            transform: translateY(-5px);
        }
        .stats-icon {
            font-size: 2.5rem;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-success">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <i class="fas fa-horse"></i> Horse Feed Service
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/">
                            <i class="fas fa-home"></i> Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/horses">
                            <i class="fas fa-horse"></i> Horses
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/schedules">
                            <i class="fas fa-calendar-alt"></i> Feeding Schedules
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container-fluid">
        <!-- Hero Section -->
        <div class="hero-section text-center">
            <div class="container">
                <h1 class="display-4 fw-bold">Horse Feed Service</h1>
                <p class="lead">Manage your horses' nutrition and feeding schedules with ease</p>
                <a href="${pageContext.request.contextPath}/horses/new" class="btn btn-light btn-lg me-3">
                    <i class="fas fa-plus"></i> Add New Horse
                </a>
                <a href="${pageContext.request.contextPath}/schedules/new" class="btn btn-outline-light btn-lg">
                    <i class="fas fa-calendar-plus"></i> Create Schedule
                </a>
            </div>
        </div>

        <div class="container">
            <!-- Flash Messages -->
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="fas fa-check-circle"></i> ${successMessage}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>
            
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="fas fa-exclamation-circle"></i> ${errorMessage}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <!-- Statistics Cards -->
            <div class="row mb-5">
                <div class="col-md-4 mb-4">
                    <div class="card stats-card text-center h-100">
                        <div class="card-body">
                            <div class="stats-icon text-primary">
                                <i class="fas fa-horse"></i>
                            </div>
                            <h3 class="card-title">${horseCount}</h3>
                            <p class="card-text text-muted">Total Horses</p>
                            <a href="${pageContext.request.contextPath}/horses" class="btn btn-outline-primary">
                                View All Horses
                            </a>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4 mb-4">
                    <div class="card stats-card text-center h-100">
                        <div class="card-body">
                            <div class="stats-icon text-success">
                                <i class="fas fa-seedling"></i>
                            </div>
                            <h3 class="card-title">${feedTypeCount}</h3>
                            <p class="card-text text-muted">Feed Types</p>
                            <a href="#" class="btn btn-outline-success">
                                Manage Feed Types
                            </a>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4 mb-4">
                    <div class="card stats-card text-center h-100">
                        <div class="card-body">
                            <div class="stats-icon text-warning">
                                <i class="fas fa-calendar-alt"></i>
                            </div>
                            <h3 class="card-title">${scheduleCount}</h3>
                            <p class="card-text text-muted">Active Schedules</p>
                            <a href="${pageContext.request.contextPath}/schedules" class="btn btn-outline-warning">
                                View Schedules
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Recent Horses -->
            <div class="row">
                <div class="col-lg-6 mb-4">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h5 class="mb-0">
                                <i class="fas fa-horse text-primary"></i> Recent Horses
                            </h5>
                            <a href="${pageContext.request.contextPath}/horses" class="btn btn-sm btn-outline-primary">
                                View All
                            </a>
                        </div>
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${not empty recentHorses}">
                                    <div class="list-group list-group-flush">
                                        <c:forEach items="${recentHorses}" var="horse" varStatus="status">
                                            <c:if test="${status.index < 5}">
                                                <div class="list-group-item d-flex justify-content-between align-items-center">
                                                    <div>
                                                        <h6 class="mb-1">${horse.name}</h6>
                                                        <small class="text-muted">${horse.breed} • ${horse.age} years • ${horse.weight} kg</small>
                                                    </div>
                                                    <span class="badge bg-${horse.activityLevel == 'HIGH' ? 'danger' : horse.activityLevel == 'MODERATE' ? 'warning' : 'success'} rounded-pill">
                                                        ${horse.activityLevel}
                                                    </span>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="text-center py-4">
                                        <i class="fas fa-horse fa-3x text-muted mb-3"></i>
                                        <p class="text-muted">No horses registered yet.</p>
                                        <a href="${pageContext.request.contextPath}/horses/new" class="btn btn-primary">
                                            Add Your First Horse
                                        </a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <!-- Recent Feeding Schedules -->
                <div class="col-lg-6 mb-4">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h5 class="mb-0">
                                <i class="fas fa-calendar-alt text-success"></i> Recent Schedules
                            </h5>
                            <a href="${pageContext.request.contextPath}/schedules" class="btn btn-sm btn-outline-success">
                                View All
                            </a>
                        </div>
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${not empty recentSchedules}">
                                    <div class="list-group list-group-flush">
                                        <c:forEach items="${recentSchedules}" var="schedule" varStatus="status">
                                            <c:if test="${status.index < 5}">
                                                <div class="list-group-item">
                                                    <div class="d-flex w-100 justify-content-between">
                                                        <h6 class="mb-1">${schedule.horseName}</h6>
                                                        <small class="text-muted">${schedule.feedingTime}</small>
                                                    </div>
                                                    <p class="mb-1">${schedule.feedTypeName} - ${schedule.quantityKg} kg</p>
                                                    <small class="text-muted">${schedule.frequency}</small>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="text-center py-4">
                                        <i class="fas fa-calendar-alt fa-3x text-muted mb-3"></i>
                                        <p class="text-muted">No feeding schedules created yet.</p>
                                        <a href="${pageContext.request.contextPath}/schedules/new" class="btn btn-success">
                                            Create Your First Schedule
                                        </a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-light mt-5 py-4">
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <p class="text-muted mb-0">&copy; 2025 Horse Feed Service. Built with Spring MVC & Bootstrap.</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
