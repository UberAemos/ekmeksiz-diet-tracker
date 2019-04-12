import React, { Component } from 'react'
import { Formik, Field, Form, ErrorMessage } from 'formik'
import UserDataService from '../api/UserDataService'

export default class SignUpComponent extends Component {
  constructor(props) {
    super(props)

    this.createUser = this.createUser.bind(this)
    this.onSubmit = this.onSubmit.bind(this)
    this.validate = this.validate.bind(this)
  }

  createUser(values) {
    let user = {
      name: values.name,
      username: values.username,
      password: values.password
    }
    return user
  }

  onSubmit(values) {
    let user = this.createUser(values)
    UserDataService.createUser(user)
      .then(() => this.props.history.push('/login'))
  }

  validate(values) {
    let user = this.createUser(values)
    return UserDataService.validateUser(user)
  }

  render() {
    return (
      <div>
        <Formik
          onSubmit={this.onSubmit}
          validateOnChange={false}
          validateOnBlue={false}
          validate={this.validate}
        >

          <Form className="form-signup w-25 mx-auto">
            <h1 className="h3 mb-3 font-weight-normal">Sign Up</h1>
            <ErrorMessage name="name" component="div"       className="alert alert-warning" />
            <ErrorMessage name="username" component="div" className="alert alert-warning" />
            <ErrorMessage name="password" component="div" className="alert alert-warning" />
            <fieldset className="form-group">
              <Field className="form-control" type="text" name="name" placeholder="Name" />
            </fieldset>
            <fieldset className="form-group">
              <Field className="form-control" type="text" name="username" placeholder="Username" />
            </fieldset>
            <fieldset className="form-group">
              <Field className="form-control" type="text" name="password" placeholder="Password" />
            </fieldset>
            <button type="submit" className="btn btn-success">Sign Up</button>
          </Form>
        </Formik>
      </div>
    )
  }
}
