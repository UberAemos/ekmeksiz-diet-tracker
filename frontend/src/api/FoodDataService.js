import axios from 'axios'
import {API_URL, EDAMAM_ID, EDAMAM_KEY, EDAMAM_URL} from '../Constants'


class FoodDataService {
    searchFood(food) {
        let url = EDAMAM_URL + food + "&app_id=" + EDAMAM_ID + "&app_key=" + EDAMAM_KEY
        return axios.get(url)
    }

    addFood(username, course, food) {
        return axios.post(`${API_URL}/foods/${username}/${course}`, food)
    }

    getFoods(username) {
        return axios.get(`${API_URL}/foods/${username}`)
    }
}

export default new FoodDataService()