import axios from "axios"
import {API_URL} from '../Constants'

class AdminService {
    getUsers() {
        return axios.get(`${API_URL}/admin/users`)
    }

    deleteUser(id) {
        return axios.delete(`${API_URL}/admin/users/${id}`)
    }
}

export default new AdminService()

