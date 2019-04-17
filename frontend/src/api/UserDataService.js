import axios from 'axios'
import {API_URL} from '../Constants'

class UserDataService {

  createUser(user) {
    return axios.post(`${API_URL}/register`, user)
  }

  validateUser(user) {
    return axios.post(`${API_URL}/register/validate`, user)
  }
}

export default new UserDataService()