import axios from 'axios'
import {API_URL} from '../Constants'

class AuthenticationService {

    registerSuccessfulLogin(username, token) {
        sessionStorage.setItem("USERNAME", username)
        sessionStorage.setItem("ACCESS_TOKEN", token)
    }

    createUser(signupForm) {
        return axios.post(`${API_URL}/auth/signup`, signupForm)
    }

    createJWTToken(token) {
        return 'Bearer ' + token
    }

    logout() {
        sessionStorage.removeItem("USERNAME")
        sessionStorage.removeItem("ACCESS_TOKEN")
        axios.interceptors.request.eject()
        window.location.pathname = "/"
    }

    validateUser(signupForm) {
        return axios.post(`${API_URL}/auth/validate`, signupForm)
    }
    
    loginUser(loginForm) {
        return axios.post(`${API_URL}/auth/signin`, loginForm)
    }

    getLoggedInUsername() {
        let user = sessionStorage.getItem("USERNAME")
        if (user === null) return "Guest"
        return user
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem("USERNAME")
        return !(user === null)
    }

    setupAxiosInterceptors() {
        let token = sessionStorage.getItem("ACCESS_TOKEN")
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.Authorization = this.createJWTToken(token)
                }
                return config
            }
        )
    }
}

export default new AuthenticationService()