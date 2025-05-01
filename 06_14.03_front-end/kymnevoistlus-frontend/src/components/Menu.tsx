import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { Link } from 'react-router-dom';

function Menu() {
  return (
    <Navbar collapseOnSelect expand="lg" fixed='top' className="bg-body-tertiary">
      <Container>
        <Navbar.Brand as={Link} to="/">Kümnevõistlus</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav items-center" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/athletes">Athletes</Nav.Link>
            <NavDropdown title="Admin" id="collapsible-nav-dropdown">
              <NavDropdown.Item as={Link} to="/admin/athletes">Manage Athletes</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Menu;