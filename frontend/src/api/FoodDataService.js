import axios from 'axios'
import {API_URL} from '../Constants'

class FoodDataService {
    searchFood(food) {
        return axios.post(`${API_URL}/foods`, food)
    }

    addFood(username, course, food) {
        return axios.post(`${API_URL}/foods/${username}/${course}`, food)
    }

    getFoods(username) {
        return axios.get(`${API_URL}/foods/${username}`)
    }
}

export default new FoodDataService()