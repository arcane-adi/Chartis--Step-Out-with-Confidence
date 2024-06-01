import pandas as pd
from math import isclose

# Function to truncate to two decimal places
def truncate(number, digits):
    stepper = 10.0 ** digits
    return int(number * stepper) / stepper

# Function to match latitude and longitude
def match_lat_lon(input_lat, input_lon):
    input_file = 'pune_data.xlsx'
    df = pd.read_excel(input_file)
    input_lat = truncate(input_lat, 2)
    input_lon = truncate(input_lon, 2)

    for index, row in df.iterrows():
        row_lat = truncate(row['Latitude'], 2)
        row_lon = truncate(row['Longitude'], 2)
        if isclose(row_lat, input_lat) and isclose(row_lon, input_lon):
            return {
                "Place": row['Place'],
                "Danger Issues": row['Danger Issues'],
                "Precautions": row['Precautions']
            }

    return "No match found"