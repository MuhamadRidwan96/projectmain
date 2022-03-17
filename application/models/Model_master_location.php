<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Model_master_location extends CI_Model
{

  function get_all_location($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_location');
    if ($search) {
      $this->db->like('bin', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }

  function get_master_location($bin)
  {
    $this->db->select('*');
    $this->db->from('tb_location');
    $this->db->where('bin', $bin);
    $query = $this->db->get();
    return $query->row_array();
  }

  // untuk men-generate kode
  function get_code_location()
  {
    $this->db->select_max('bin');
    $query = $this->db->get('tb_location');
    return $query->row_array();
  }
  // end untuk men-generate kode

  // untuk data json
  function get_location($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_location');
    if ($search) {
      $this->db->like('bin', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }
  // untuk data json
}
