"""
This code fetch the values of specified keys from a nested JSON file recursively.
Input: JSON file
Output: List of values
"""

import json


def json_extract(json_object):
    """Recursively fetch values from nested JSON."""
    values_list = []

    def extract(obj):
        """Recursively search for values of key in JSON tree."""
        resource_id = ""
        text = ""
        # Provide the keys for which you want the values.
        if "resource-id" in obj:
            resource_id = obj["resource-id"]
        if "text" in obj:
            text = obj["text"]
        values_list.append({"resource-id": resource_id, "text": text})

        # Provide the key for which you want to get the values recursively.
        if "children" in obj:
            children = obj['children']
            for elements in children:
                extract(elements)

    extract(json_object)
    return values_list


if __name__ == '__main__':
    json_file_path = '../data/example.json'
    with open(json_file_path, 'r') as json_file:
        json_file_data = json.load(json_file)
        activity = json_file_data["activity"]
        root = activity["root"]
        values = json_extract(root)     # I am providing "root" as the JSON object. You can give "json_file_data" instead.
        print(values.__len__())
        print(values)
