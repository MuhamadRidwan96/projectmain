<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Model_master_material extends CI_Model
{
  function get_all_material($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_material');
    if ($search) {
      $this->db->like('mm', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }

  function get_master_material($mm)
  {
    $this->db->select('*');
    $this->db->from('tb_material');
    $this->db->where('mm', $mm);
    $query = $this->db->get();
    return $query->row_array();
  }

  // untuk men-generate kode
  function get_mm_material()
  {
    $this->db->select_max('mm');
    $query = $this->db->get('tb_material');
    return $query->row_array();
  }
  // end untuk men-generate kode

  // untuk data json
  function get_material($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_material');
    if ($search) {
      $this->db->like('mm', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }
  // untuk data json
}
