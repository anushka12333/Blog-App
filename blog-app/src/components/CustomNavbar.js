import {NavLink as ReactLink, useNavigate} from 'react-router-dom'
import React, { useState,useEffect } from 'react';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavLink,
  NavItem,
  NavbarText
} from "reactstrap";
import { doLogout, getCurrentUSerdetail, isLoggedIn } from '../auth';
const CustomNavbar=()=>{
  let navigate=useNavigate();

  const [collapsed, setCollapsed] = useState(true);
  const toggleNavbar = () => setCollapsed(!collapsed);
  const [login, setLogin] = useState(false)
  const [user, setUser] = useState(undefined)
 
  useEffect(() => {
    setLogin(isLoggedIn())
    setUser(getCurrentUSerdetail())
  }, [login])

const logout =()=>{
  doLogout(()=>{
    //loggout 
    setLogin(false);
    console.log('jhhj');
    
    navigate("/");

  })
}

    return (
      <div>
      <Navbar 
      color="dark" 
      fixed="top" 
      dark
       expand={"lg"} 
       fixed="" 
       className='px-5'>
          <NavbarBrand href="/">MyBlogs</NavbarBrand>
          <NavbarToggler onClick={toggleNavbar} />
          <Collapse navbar isOpen={!collapsed}>
            <Nav className="me-auto" navbar>
              <NavItem>
                <NavLink  tag ={ReactLink} to="/">NewFeed</NavLink>
              </NavItem>
              <NavItem>
                <NavLink  tag ={ReactLink} to="/about">About</NavLink>
              </NavItem>
              
              <UncontrolledDropdown inNavbar nav>
                <DropdownToggle caret nav>
                  More
                </DropdownToggle>
                <DropdownMenu right>
                  <DropdownItem tag ={ReactLink} to="/services">
                  Contact Us
                  </DropdownItem>
                  <DropdownItem>Facebook</DropdownItem>
                  <DropdownItem divider />
                  <DropdownItem tag ={ReactLink} to="/services">
                  Linkdin
                  </DropdownItem>
                  <DropdownItem tag ={ReactLink} to="/services">
                 Services
                  </DropdownItem>
                </DropdownMenu>
              </UncontrolledDropdown>
            </Nav>
            <Nav navbar>
            {
              login && (
               <>
               <NavItem>                
              <NavLink  tag ={ReactLink} to="/user/profile-info" >Profile Info</NavLink>               
              </NavItem>
            
                <NavItem>                
              <NavLink  tag ={ReactLink} to="/user/dashboard">
              {user.email} 
              </NavLink>               
              </NavItem>
              <NavItem>               
              <NavLink onClick={logout} >Logout</NavLink>               
              </NavItem>
               </>
              )
            }
            {
              !login && (
                <>
                <NavItem>                
              <NavLink tag ={ReactLink} to="/login">Login</NavLink>               
              </NavItem>
              <NavItem>
                <NavLink tag ={ReactLink} to="/signup">SignUp</NavLink>
              </NavItem></>
              )
            }
            </Nav>
           
          </Collapse>
        </Navbar>
    </div>
    );
}
export default CustomNavbar;