import { render, screen, fireEvent } from '@testing-library/react';
import React from 'react';
import AddProduct from './src/components/Products/AddProduct';

describe('AddProduct', () => {
  it('renders the AddProduct component', () => {
    render(<AddProduct open={true} handleClose={() => {}} />);
    expect(screen.getByText('Add new product')).toBeInTheDocument();
  });

  it('updates the name input value', () => {
    render(<AddProduct open={true} handleClose={() => {}} />);
    const nameInput = screen.getByLabelText('product name');
    fireEvent.change(nameInput, { target: { value: 'Test Product' } });
    expect(nameInput.value).toBe('Test Product');
  });

  it('updates the max measure input value', () => {
    render(<AddProduct open={true} handleClose={() => {}} />);
    const maxMeasureInput = screen.getByLabelText('Max measure');
    fireEvent.change(maxMeasureInput, { target: { value: '10' } });
    expect(maxMeasureInput.value).toBe('10');
  });

  it('updates the min measure input value', () => {
    render(<AddProduct open={true} handleClose={() => {}} />);
    const minMeasureInput = screen.getByLabelText('Min measure');
    fireEvent.change(minMeasureInput, { target: { value: '5' } });
    expect(minMeasureInput.value).toBe('5');
  });

  it('calls the addProduct function when Add button is clicked', () => {
    const addProductMock = jest.fn();
    render(<AddProduct open={true} handleClose={() => {}} />);
    const addButton = screen.getByText('Add');
    fireEvent.click(addButton);
    expect(addProductMock).toHaveBeenCalled();
  });
});