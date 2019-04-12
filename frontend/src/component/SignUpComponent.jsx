import React, { Component } from 'react'
import {Formik, Field, Form} from 'formik'

export default class SignUpComponent extends Component {
  render() {
    return (
      <div>
        <Formik>
          <Form className="form-signup w-25 mx-auto">
          <h1 className="h3 mb-3 font-weight-normal">Sign Up</h1>
          <fieldset className="form-group">
          <Field className="form-control" type="text" name="name" placeholder="Name" />
          </fieldset>
          <fieldset className="form-group">
          <Field className="form-control" type="text" name="surname" placeholder="Surname" />
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
