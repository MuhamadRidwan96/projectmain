<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Users_model extends CI_Model
{
    function get_all_user($search = null)
    {
        $this->db->select('*');
        $this->db->from('tb_users');
        if ($search) {
            $this->db->where('nik', $search);
        }
        $query = $this->db->get();
        return $query->result_array();
    }

    function getUser($nik)
    {
      $this->db->select('*');
      $this->db->from('tb_users');
      $this->db->where('nik', $nik);
      $query = $this->db->get();
      return $query->row_array();
    }
// untuk men-generate kode
    function get_username()
  {
    $this->db->select_max('nik');
    $query = $this->db->get('tb_users');
    return $query->row_array();
  }
  // end untuk men-generate kode

  // untuk data json
  function get_user($search = null)
  {
    $this->db->select('*');
    $this->db->from('tb_users');
    if ($search) {
      $this->db->like('nik', $search);
    }
    $query = $this->db->get();
    return $query->result_array();
  }

}
