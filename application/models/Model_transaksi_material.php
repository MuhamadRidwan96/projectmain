<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Model_transaksi_material extends CI_Model
{
  // start model for material IN
  function get_material_in($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_migo');
    if ($search) {
      $this->db->like('no_doc', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }

  function get_transaksi_material_in($no_doc)
  {
    $this->db->select('*');
    $this->db->from('tb_migo');
    $this->db->where('no_doc', $no_doc);
    $query = $this->db->get();
    return $query->row_array();
  }

  // untuk men-generate kode material in
  function get_kode_material_in()
  {
    $this->db->select_max('no_doc');
    $query = $this->db->get('tb_migo');
    return $query->row_array();
  }
  // end untuk men-generate kode material in
  // end model for material IN

  /* =============================================== */

  // start model for material OUT
  function get_material_out($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_consumption');
    if ($search) {
      $this->db->like('mat_doc', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }

  function get_transaksi_material_out($id)
  {
    $this->db->select('*');
    $this->db->from('tb_consumption');
    $this->db->where('mat_doc', $id);
    $query = $this->db->get();
    return $query->row_array();
  }

  // untuk men-generate kode material in
  function get_kode_material_out()
  {
    $this->db->select_max('mat_doc');
    $query = $this->db->get('tb_consumption');
    return $query->row_array();
  }
  // end untuk men-generate kode material out
  // end model for material OUT

  // untuk data json
  function get_json_material_in($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_consumption');
    if ($search) {
      $this->db->like('mat_doc', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }

  function get_json_material_out($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_consumption');
    if ($search) {
      $this->db->like('mat_doc', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }
  // untuk data json
}
