import unittest

def check_subtree():
    pass


class TestCheckSubtree(unittest.TestCase):

    def test_fail(self):
        self.assertTrue(1 == 0)


if __name__== "__main__":
    unittest.main(verbosity=2)
