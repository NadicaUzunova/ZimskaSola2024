import { render, screen, fireEvent } from '@testing-library/react';
import React from 'react';
import AddMeasurement from './src/components/Measurements/AddMeasurement';

describe('AddMeasurement', () => {
  it('renders the component', () => {
    render(<AddMeasurement open={true} handleClose={() => {}} />);
    expect(screen.getByText('Add new measurement')).toBeInTheDocument();
  });

  it('displays product options', async () => {
    const mockProducts = [
      { id: 1, name: 'Product 1' },
      { id: 2, name: 'Product 2' },
    ];
    const mockGetProducts = jest.fn().mockResolvedValue({ data: mockProducts });
    jest.mock('./MeasurementsApi', () => {
      return jest.fn().mockImplementation(() => {
        return { getProducts: mockGetProducts };
      });
    });

    render(<AddMeasurement open={true} handleClose={() => {}} />);
    expect(mockGetProducts).toHaveBeenCalledTimes(1);
    expect(await screen.findByText('Product 1')).toBeInTheDocument();
    expect(await screen.findByText('Product 2')).toBeInTheDocument();
  });

  it('adds a new measurement', async () => {
    const mockPostMeasurement = jest.fn().mockResolvedValue({ request: { status: 200 } });
    jest.mock('./MeasurementsApi', () => {
      return jest.fn().mockImplementation(() => {
        return { postMeasurement: mockPostMeasurement };
      });
    });

    render(<AddMeasurement open={true} handleClose={() => {}} />);
    fireEvent.change(screen.getByLabelText('Product'), { target: { value: '1' } });
    fireEvent.change(screen.getByLabelText('average temperature'), { target: { value: '25' } });
    fireEvent.click(screen.getByTestId('submitMeasurementButton'));

    expect(mockPostMeasurement).toHaveBeenCalledTimes(1);
    expect(mockPostMeasurement).toHaveBeenCalledWith('1', '25');
    // Add additional assertions as needed
  });
});