import axios from 'axios'
import {API_URL} from '../Constants'

class UserDataService {

  validateUser(user) {
    return axios.post(`${API_URL}/users/register/validate`, user)
  }

  createUser(user) {
    return axios.post(`${API_URL}/register`, user)
  }
}

export default new UserDataService()