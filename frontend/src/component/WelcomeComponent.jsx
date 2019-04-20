import React from 'react'
import AuthenticationService from '../api/AuthenticationService';

export default function WelcomeComponent() {
    let username = AuthenticationService.getLoggedInUsername()
  return (
    <div>
      Welcome {username} to Ekmeksiz App, click <a href={"/" + username}>here</a> to get to your diet list
    </div>
  )
}
