<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

  <!-- Sidebar - Brand -->
  <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
    <div class="sidebar-brand-icon rotate-n-15">
      <i class="fas fa-laugh-wink"></i>
    </div>
    <div class="sidebar-brand-text mx-3">Admin <sup>2</sup></div>
  </a>

  <!-- Divider -->
  <hr class="sidebar-divider my-0">

  <!-- Nav Item - Dashboard -->
  <li class="nav-item active">
    <a class="nav-link" href="<?= base_url('stocks/index'); ?>">
      <i class="fas fa-fw fa-tachometer-alt"></i>
      <span>Available Stock</span></a>
  </li>


  <!-- =========================================== -->

  <!-- start Menu Master -->
  <!-- Divider -->
  <hr class="sidebar-divider">

  <!-- Heading -->
  <div class="sidebar-heading">
    Master
  </div>

  <!-- Nav Item - Pages Collapse Menu -->
  <li class="nav-item">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMaster" aria-expanded="true" aria-controls="collapseMaster">
      <i class="fas fa-fw fa-cog"></i>
      <span>Master</span>
    </a>
    <div id="collapseMaster" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
      <div class="bg-white py-2 collapse-inner rounded">
        <h6 class="collapse-header">Menu Master:</h6>
        <a class="collapse-item" href="<?= base_url('master_material/index'); ?>">Master Material</a>
        <a class="collapse-item" href="<?= base_url('master_location/index');?>">Master Location</a>
      </div>
    </div>
  </li>
  <!-- end Menu Master -->

  <!-- start Menu Transaksi -->
  <!-- Divider -->
  <hr class="sidebar-divider">

  <!-- Heading -->
  <div class="sidebar-heading">
    Transaksi
  </div>

  <!-- Nav Item - Pages Collapse Menu -->
  <li class="nav-item">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTransaksi" aria-expanded="true" aria-controls="collapseTransaksi">
      <i class="fas fa-fw fa-cog"></i>
      <span>Transaksi</span>
    </a>
    <div id="collapseTransaksi" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
      <div class="bg-white py-2 collapse-inner rounded">
        <h6 class="collapse-header">Menu Transaksi:</h6>
        <a class="collapse-item" href="<?= base_url('transaksi_material/material_in'); ?>">GOOD ISSUE</a>
        <a class="collapse-item" href="<?= base_url('transaksi_material/material_out'); ?>">CONSUMPTION</a>
      </div>
    </div>
  </li>
  <!-- end Menu Transaksi -->

  <!-- start Menu SysConfig -->
  <!-- Divider -->
  <hr class="sidebar-divider">

  <!-- Heading -->
  <div class="sidebar-heading">
    SysConfig
  </div>

  <!-- Nav Item - Pages Collapse Menu -->
  <li class="nav-item">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSysConfig" aria-expanded="true" aria-controls="collapseSysConfig">
      <i class="fas fa-fw fa-cog"></i>
      <span>SysConfig</span>
    </a>
    <div id="collapseSysConfig" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
      <div class="bg-white py-2 collapse-inner rounded">
        <h6 class="collapse-header">Menu SysConfig:</h6>
        <a class="collapse-item" href="<?= base_url('sysconfig/index'); ?>">Users</a>
      </div>
    </div>
  </li>
  <!-- end Menu SysConfig -->
  <!-- =========================================== -->

  <!-- Divider -->
  <hr class="sidebar-divider d-none d-md-block">

  <!-- Sidebar Toggler (Sidebar) -->
  <div class="text-center d-none d-md-inline">
    <button class="rounded-circle border-0" id="sidebarToggle"></button>
  </div>

</ul>
<!-- End of Sidebar -->

<!-- End of Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">

  <!-- Main Content -->
  <div id="content">

    <?php
    require('topbar.php');
    ?>