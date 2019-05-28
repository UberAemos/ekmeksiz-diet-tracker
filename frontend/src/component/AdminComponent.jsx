import React, { Component } from "react";
import moment from "moment"
import AdminService from "../api/AdminService";

export default class AdminComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      users: ""
    };
  }

  componentDidMount() {
    AdminService.getUsers().then(response =>
      this.setState({
        users: response.data
      })
    );
  }

  deleteUser(id) {
    AdminService.deleteUser(id)
        .then(response => 
            this.setState({
                users: response.data
            }))
  }

  render() {
    return (
        
      <div className="h-75 w-75 mx-auto overflow-auto d-flex flex-column my-2">
        <table className="table-sm mx-1">
          <thead>
            <tr>
              <th className="border-bottom border-dark">Username</th>
              <th className="border-bottom border-dark">Role</th>
              <th className="border-bottom border-dark">Registered</th>
              <th className="border-bottom border-dark">Last Login</th>
              <th className="border-bottom border-dark">DailyDiets</th>
            </tr>
          </thead>
          {this.state.users && (
          <tbody>
            {this.state.users.map((user, index) => (
              <tr>
                <td>
                  <div className="d-flex flex-row align-items-center justify-content-center">
                    <button
                      className="btn btn-danger"
                      onClick={() => this.deleteUserClicked(user.id)}
                    >
                      -
                    </button>
                    <span>{user.username}</span>
                  </div>
                </td>
                <td>{user.roles[0].name}</td>
                <td>{moment(user.registerDate).format("YYYYMMDD")}</td>
                <td>{moment(user.loginDate).format("YYYYMMDD")}</td>
                <td>{user.dietList.length}</td>
              </tr>
            ))}
          </tbody>
          )}
        </table>
      </div>
    );
  }
}
