<?php

class Auth_model extends CI_Model
{
    function getAkses($username, $password)
    {
        $data = [
            'username' => $username,
            'password' => $password
        ];
        return $this->db->get_where('tb_users', $data)->row_array();
    }
}
