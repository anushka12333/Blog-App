import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import {BrowserRouter,Routes,Route} from 'react-router-dom'
import Home from './pages/Home';
import SignUp from './pages/SignUp';
import Login from './pages/Login';
import About from './pages/About';
import Services from './pages/Services';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import PrivateRoute from './components/PrivateRoute';
import UserDashboard from './pages/user_routes/UserDashboard';
import ProfileInfo from './pages/user_routes/ProfileInfo';
import PostPage from './pages/PostPage';
import UserProvider from './context/UserProvider';
import Categories from './pages/Categories';

function App() {
  return (
  <UserProvider>
 <BrowserRouter>
 <ToastContainer position='top-center'/>
  <Routes>
    <Route path='/' element={<Home />} />
    <Route path='/login' element={<Login />}/>
    <Route path='/signup' element={<SignUp />}  />
    <Route path='/about' element={<About />}  />
    <Route path='/services' element={<Services />}  />   
    <Route path='/posts/:postId' element={<PostPage />}  />  
    <Route path='/categories/:categoryId' element={<Categories />} /> 
    <Route path='/user' element={<PrivateRoute />}  >
      <Route path='dashboard' element={<UserDashboard />} />
      <Route path='profile-info' element={<ProfileInfo/>} />
    </Route>

  </Routes>
 </BrowserRouter>
 </UserProvider>
  );
}

export default App;
