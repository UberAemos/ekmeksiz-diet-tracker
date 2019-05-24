import axios from 'axios'
import {API_URL, EDAMAM_ID, EDAMAM_KEY, EDAMAM_URL, EDAMAM_NUTRIENTS_URL} from '../Constants'


class FoodDataService {
    /* Edamam parser request */
    searchFood(food) {
        let url = `${EDAMAM_URL}${food}&app_id=${EDAMAM_ID}&app_key=${EDAMAM_KEY}`
        return axios.get(url)
    }

    /* Edamam nutrition data request */
    getNutrients(quantity, measureURI, foodId) {
        let request = {
            ingredients : [{
                quantity,
                measureURI,
                foodId
            }]
        }
        return axios.post(EDAMAM_NUTRIENTS_URL, request)
    }

    addFood(username, date, course, food) {
        return axios.post(`${API_URL}/users/${username}/${date}/${course}`, food)
    }

    getDate(username, date) {
        return axios.get(`${API_URL}/users/${username}/${date}`)
    }

    getDefaultDate(date) {
        return axios.get(`${API_URL}/guest/${date}`)
    }

    deleteFood(username, date, course, id) {
        return axios.delete(`${API_URL}/users/${username}/${date}/${course}/${id}`)
    }
}

export default new FoodDataService()