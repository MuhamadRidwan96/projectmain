<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Model_master_stocks extends CI_Model
{

  function get_all_stocks($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_stock');
    if ($search) {
      $this->db->like('id_stock', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }

  function get_stocks($id_stock)
  {
    $this->db->select('*');
    $this->db->from('tb_stock');
    $this->db->where('id_stock', $id_stock);
    $query = $this->db->get();
    return $query->row_array();
  }

  // untuk men-generate kode
  function get_code_stocks()
  {
    $this->db->select_max('id_stock');
    $query = $this->db->get('tb_stock');
    return $query->row_array();
  }
  // end untuk men-generate kode

  // untuk data json
  function get_stock($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_stock');
    if ($search) {
      $this->db->like('id_stock', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }
  // untuk data json
}
