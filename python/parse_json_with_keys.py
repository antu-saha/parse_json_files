"""
This code fetch the values of a given key from a nested JSON file recursively.
Input: JSON file and a Key
Output: List of values for the Key
"""

import json


def json_extract(json_object, key):
    """Recursively fetch values from nested JSON."""
    values_list = []

    def extract(obj, value, key):
        """Recursively search for values of key in JSON tree."""
        if isinstance(obj, dict):
            for k, v in obj.items():
                if isinstance(v, (dict, list)):
                    extract(v, value, key)
                elif k == key:
                    value.append(v)

        elif isinstance(obj, list):
            for item in obj:
                extract(item, value, key)
        return value

    values = extract(json_object, values_list, key)
    return values


if __name__ == '__main__':
    json_file_path = '../data/example.json'
    with open(json_file_path, 'r') as json_file:
        json_file_data = json.load(json_file)
        values_for_given_key = json_extract(json_file_data, "text")
        print(values_for_given_key.__len__())
        print(values_for_given_key)
